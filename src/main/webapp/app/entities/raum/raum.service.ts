import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRaum } from 'app/shared/model/raum.model';

type EntityResponseType = HttpResponse<IRaum>;
type EntityArrayResponseType = HttpResponse<IRaum[]>;

@Injectable({ providedIn: 'root' })
export class RaumService {
  public resourceUrl = SERVER_API_URL + 'api/raum';

  constructor(protected http: HttpClient) {}

  create(raum: IRaum): Observable<EntityResponseType> {
    return this.http.post<IRaum>(this.resourceUrl, raum, { observe: 'response' });
  }

  update(raum: IRaum): Observable<EntityResponseType> {
    return this.http.put<IRaum>(this.resourceUrl, raum, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRaum>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRaum[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
