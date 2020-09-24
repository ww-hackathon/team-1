import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBuchung } from 'app/shared/model/buchung.model';
import { Moment } from 'moment';

type EntityResponseType = HttpResponse<IBuchung>;
type EntityArrayResponseType = HttpResponse<IBuchung[]>;

@Injectable({ providedIn: 'root' })
export class BuchungService {
  public resourceUrl = SERVER_API_URL + 'api/buchungen';

  constructor(protected http: HttpClient) {}

  create(buchung: IBuchung): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(buchung);
    return this.http
      .post<IBuchung>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(buchung: IBuchung): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(buchung);
    return this.http
      .put<IBuchung>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBuchung>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByUserd(userId: number): Observable<EntityResponseType> {
    return this.http.get<IBuchung>(`${this.resourceUrl}/user/${userId}`, { observe: 'response' });
  }

  findByRoomAndDate(roomId: number, date: Moment): Observable<EntityResponseType> {
    const formattedDate = this.formatDate(date);
    return this.http
      .get<IBuchung>(`${this.resourceUrl}/${formattedDate}/raum/${roomId}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBuchung[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(buchung: IBuchung): IBuchung {
    const copy: IBuchung = Object.assign({}, buchung, {
      datum: this.formatDate(buchung.datum),
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datum = res.body.datum ? moment(res.body.datum) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((buchung: IBuchung) => {
        buchung.datum = buchung.datum ? moment(buchung.datum) : undefined;
      });
    }
    return res;
  }

  private formatDate(date?: Moment): string {
    return date && date.isValid() ? date.format(DATE_FORMAT) : '';
  }
}
