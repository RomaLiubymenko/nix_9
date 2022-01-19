import {Directive, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpHeaders, HttpResponse} from "@angular/common/http";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {AbstractModel} from "../../models/abstract.model";
import {AbstractService} from "../../services/abstract.service";
import {IPagination} from "../../utils/request/param-util";
import {ConfirmationService, MessageService, SortEvent} from "primeng/api";

@Directive()
export abstract class AbstractTableComponent<ENTITY extends AbstractModel> implements OnInit {

  entities: ENTITY[] = [];
  selectedEntity!: ENTITY;
  totalItems = 0;
  itemsPerPage = 5;
  page!: number;
  predicate = 'created';
  isAscending!: boolean;

  protected constructor(
    public entryService: AbstractService<ENTITY>,
    public router: Router,
    public activatedRoute: ActivatedRoute,
    public messageService: MessageService,
    public confirmationService: ConfirmationService) {
  }

  ngOnInit(): void {
    this.handleNavigation();
  }

  handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).pipe(map(([data, params]) => {
      const page = params.get('page');
      this.page = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      this.predicate = sort[0];
      this.isAscending = sort[1] === 'asc';
      this.transition();
      this.loadAll();
    })).subscribe();
  }

  transition(): void {
    if (Array.isArray(this.entities)) {
      let queryParams: { page?: number, sort?: string, query?: string } = {
        page: this.page,
        sort: this.predicate + ',' + (this.isAscending ? 'asc' : 'desc'),
      };
      this.router.navigate(['./'], {
        relativeTo: this.activatedRoute.parent,
        queryParams: queryParams,
      });
    }
  }

  sortBy(event: SortEvent) {
    this.predicate = event.field !== undefined ? event.field : 'created';
    this.isAscending = event.order == 1;
    this.transition();
  }

  selectRow(entity: ENTITY) {
    this.selectedEntity = entity;
  }

  loadAll(): void {
    let requestParams: IPagination = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.entryService.getAllByQuery(requestParams).subscribe({
      next: (res: HttpResponse<ENTITY[]>) => this.onSuccess(res.body, res.headers),
      error: err => console.error(err)
    });
  }

  sort(): string[] {
    return [this.predicate + ',' + (this.isAscending ? 'asc' : 'desc')];
  }

  onSuccess(entities: ENTITY[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    if (!!entities)
      this.entities = entities;
  }

  onElementDeleted(element: ENTITY) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected entries?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entryService.delete(element['uuid']!).subscribe(() => this.loadAll());
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Entries deleted successfully',
          life: 3000
        });
      }
    });
  }
}
