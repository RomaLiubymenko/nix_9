import {HttpParams} from '@angular/common/http';

export interface IPagination {
  page: number;
  size: number;
  sort: string[];
}

export const createRequestOption = (req?: any): HttpParams => {
  let options: HttpParams = new HttpParams();

  if (req) {
    Object.keys(req).forEach(key => {
      if (!req[key]) {
        return;
      }
      if (key !== 'sort') {
        options = options.set(key, req[key]);
      }
    });

    if (req.sort) {
      req.sort.forEach((val: string) => {
        options = options.append('sort', val);
      });
    }
  }
  return options;
};
