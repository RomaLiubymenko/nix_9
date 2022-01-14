import {ChangeDetectionStrategy, Component, ContentChild, Directive, EventEmitter, Input, OnChanges, Output, SimpleChanges, TemplateRef} from '@angular/core';

/**
 * A context for the
 * * `PaginationFirst`
 * * `PaginationPrevious`
 * * `PaginationNext`
 * * `PaginationLast`
 * * `PaginationEllipsis`
 *
 * link templates in case you want to override one.
 *
 * @since 4.1.0
 */
export interface PaginationLinkContext {
  /**
   * The currently selected page number
   */
  currentPage: number;

  /**
   * If `true`, the current link is disabled
   */
  disabled: boolean;
}

/**
 * A context for the `PaginationNumber` link template in case you want to override one.
 *
 * Extends `PaginationLinkContext`.
 *
 * @since 4.1.0
 */
export interface PaginationNumberContext extends PaginationLinkContext {
  /**
   * The page number, displayed by the current page link.
   */
  $implicit: number;
}

/**
 * A directive to match the 'ellipsis' link template
 *
 * @since 4.1.0
 */
@Directive({selector: 'ng-template[paginationEllipsis]'})
export class PaginationEllipsis {
  constructor(public templateRef: TemplateRef<PaginationLinkContext>) {}
}

/**
 * A directive to match the 'first' link template
 *
 * @since 4.1.0
 */
@Directive({selector: 'ng-template[paginationFirst]'})
export class PaginationFirst {
  constructor(public templateRef: TemplateRef<PaginationLinkContext>) {}
}

/**
 * A directive to match the 'last' link template
 *
 * @since 4.1.0
 */
@Directive({selector: 'ng-template[paginationLast]'})
export class PaginationLast {
  constructor(public templateRef: TemplateRef<PaginationLinkContext>) {}
}

/**
 * A directive to match the 'next' link template
 *
 * @since 4.1.0
 */
@Directive({selector: 'ng-template[paginationNext]'})
export class PaginationNext {
  constructor(public templateRef: TemplateRef<PaginationLinkContext>) {}
}

/**
 * A directive to match the page 'number' link template
 *
 * @since 4.1.0
 */
@Directive({selector: 'ng-template[paginationNumber]'})
export class PaginationNumber {
  constructor(public templateRef: TemplateRef<PaginationNumberContext>) {}
}

/**
 * A directive to match the 'previous' link template
 *
 * @since 4.1.0
 */
@Directive({selector: 'ng-template[paginationPrevious]'})
export class PaginationPrevious {
  constructor(public templateRef: TemplateRef<PaginationLinkContext>) {}
}

/**
 * A component that displays page numbers and allows to customize them in several ways.
 */
@Component({
  selector: 'pagination',
  templateUrl: './pagination.component.html',
    styleUrls: ['./pagination.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  host: {'role': 'navigation'},
})
export class PaginationComponent implements OnChanges {
  pageCount = 0;
  pages: number[] = [];

  @ContentChild(PaginationEllipsis, {static: false}) tplEllipsis: PaginationEllipsis;
  @ContentChild(PaginationFirst, {static: false}) tplFirst: PaginationFirst;
  @ContentChild(PaginationLast, {static: false}) tplLast: PaginationLast;
  @ContentChild(PaginationNext, {static: false}) tplNext: PaginationNext;
  @ContentChild(PaginationNumber, {static: false}) tplNumber: PaginationNumber;
  @ContentChild(PaginationPrevious, {static: false}) tplPrevious: PaginationPrevious;

  /**
   * If `true`, pagination links will be disabled.
   */
  @Input() disabled: boolean;

  /**
   * If `true`, the "First" and "Last" page links are shown.
   */
  @Input() boundaryLinks: boolean;

  /**
   * If `true`, the "Next" and "Previous" page links are shown.
   */
  @Input() directionLinks: boolean;

  /**
   * If `true`, the ellipsis symbols and first/last page numbers will be shown when `maxSize` > number of pages.
   */
  @Input() ellipses: boolean;

  /**
   * Whether to rotate pages when `maxSize` > number of pages.
   *
   * The current page always stays in the middle if `true`.
   */
  @Input() rotate: boolean;

  /**
   *  The number of items in your paginated collection.
   *
   *  Note, that this is not the number of pages. Page numbers are calculated dynamically based on
   *  `collectionSize` and `pageSize`. Ex. if you have 100 items in your collection and displaying 20 items per page,
   *  you'll end up with 5 pages.
   */
  @Input() collectionSize: number;

  /**
   *  The maximum number of pages to display.
   */
  @Input() maxSize: number;

  /**
   *  The current page.
   *
   *  Page numbers start with `1`.
   */
  @Input() page = 1;

  /**
   *  The number of items per page.
   */
  @Input() pageSize: number;

  /**
   *  An event fired when the page is changed. Will fire only if collection size is set and all values are valid.
   *
   *  Event payload is the number of the newly selected page.
   *
   *  Page numbers start with `1`.
   */
  @Output() pageChange = new EventEmitter<number>(true);

  constructor() {
    this.disabled = false;
    this.boundaryLinks = true;
    this.directionLinks = true;
    this.ellipses = true;
    this.maxSize = 10;
    this.pageSize = 10;
    this.rotate = false;
  }

  hasPrevious(): boolean { return this.page > 1; }

  hasNext(): boolean { return this.page < this.pageCount; }

  nextDisabled(): boolean { return !this.hasNext() || this.disabled; }

  previousDisabled(): boolean { return !this.hasPrevious() || this.disabled; }

  selectPage(pageNumber: number): void { this._updatePages(pageNumber); }

  ngOnChanges(changes: SimpleChanges): void { this._updatePages(this.page); }

  isEllipsis(pageNumber: number): boolean { return pageNumber === -1; }

  /**
   * Appends ellipses and first/last page number to the displayed pages
   */
  private _applyEllipses(start: number, end: number) {
    if (this.ellipses) {
      if (start > 0) {
        // The first page will always be included. If the displayed range
        // starts after the third page, then add ellipsis. But if the range
        // starts on the third page, then add the second page instead of
        // an ellipsis, because the ellipsis would only hide a single page.
        if (start > 2) {
          this.pages.unshift(-1);
        } else if (start === 2) {
          this.pages.unshift(2);
        }
        this.pages.unshift(1);
      }
      if (end < this.pageCount) {
        // The last page will always be included. If the displayed range
        // ends before the third-last page, then add ellipsis. But if the range
        // ends on third-last page, then add the second-last page instead of
        // an ellipsis, because the ellipsis would only hide a single page.
        if (end < (this.pageCount - 2)) {
          this.pages.push(-1);
        } else if (end === (this.pageCount - 2)) {
          this.pages.push(this.pageCount - 1);
        }
        this.pages.push(this.pageCount);
      }
    }
  }

  /**
   * Rotates page numbers based on maxSize items visible.
   * Currently selected page stays in the middle:
   *
   * Ex. for selected page = 6:
   * [5,*6*,7] for maxSize = 3
   * [4,5,*6*,7] for maxSize = 4
   */
  private _applyRotation(): [number, number] {
    let start = 0;
    let end = this.pageCount;
    let leftOffset = Math.floor(this.maxSize / 2);
    let rightOffset = this.maxSize % 2 === 0 ? leftOffset - 1 : leftOffset;

    if (this.page <= leftOffset) {
      // very beginning, no rotation -> [0..maxSize]
      end = this.maxSize;
    } else if (this.pageCount - this.page < leftOffset) {
      // very end, no rotation -> [len-maxSize..len]
      start = this.pageCount - this.maxSize;
    } else {
      // rotate
      start = this.page - leftOffset - 1;
      end = this.page + rightOffset;
    }

    return [start, end];
  }

  /**
   * Paginates page numbers based on maxSize items per page.
   */
  private _applyPagination(): [number, number] {
    let page = Math.ceil(this.page / this.maxSize) - 1;
    let start = page * this.maxSize;
    let end = start + this.maxSize;

    return [start, end];
  }

  private _setPageInRange(newPageNo: number) {
    const prevPageNo = this.page;
    this.page = Math.max(Math.min(newPageNo, this.pageCount), 1);

    if (this.page !== prevPageNo && isNumber(this.collectionSize)) {
      this.pageChange.emit(this.page);
    }
  }

  private _updatePages(newPage: number) {
    this.pageCount = Math.ceil(this.collectionSize / this.pageSize);

    if (!isNumber(this.pageCount)) {
      this.pageCount = 0;
    }

    // fill-in model needed to render pages
    this.pages.length = 0;
    for (let i = 1; i <= this.pageCount; i++) {
      this.pages.push(i);
    }

    // set page within 1..max range
    this._setPageInRange(newPage);

    // apply maxSize if necessary
    if (this.maxSize > 0 && this.pageCount > this.maxSize) {
      let start;
      let end;

      // either paginating or rotating page numbers
      if (this.rotate) {
        [start, end] = this._applyRotation();
      } else {
        [start, end] = this._applyPagination();
      }

      this.pages = this.pages.slice(start, end);

      // adding ellipses
      this._applyEllipses(start, end);
    }
  }
}

function isNumber(value: any): value is number {
  return !isNaN(parseInt(`${value}`, 10));
}

