import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {createRequestOption, IPagination} from "../utils/request/param-util";
import {httpOptions} from "../utils/request/http-body-constant";
import {AbstractModel} from "../models/abstract.model";

@Injectable({
  providedIn: 'root'
})
export class AbstractService<ENTITY extends AbstractModel> {

  protected resourceUrl = '/api';

  constructor(public httpClient: HttpClient) {
  }

  save(entity: ENTITY): Observable<any> {
    if (!!entity['uuid'])
      return this.httpClient.put(`${this.resourceUrl}/${entity.uuid}`, entity, httpOptions);
    else
      return this.httpClient.post(this.resourceUrl, entity, httpOptions);
  }

  delete(uuid: string): Observable<any> {
    return this.httpClient.delete(`${this.resourceUrl}/${uuid}`);
  }

  getByUuid(uuid: string): Observable<ENTITY> {
    return this.httpClient.get<ENTITY>(`${this.resourceUrl}/${uuid}`);
  }

  getAllByQuery(req?: IPagination): Observable<HttpResponse<ENTITY[]>> {
    const options = createRequestOption(req);
    return this.httpClient.get<ENTITY[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  getAll(): Observable<HttpResponse<ENTITY[]>> {
    let option = new HttpParams();
    option = option.set('isExistRequest', 'true');
    return this.httpClient.get<ENTITY[]>(this.resourceUrl, {params: option, observe: 'response'});
  }
}
