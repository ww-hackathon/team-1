import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { IGruppe } from '../../shared/model/gruppe.model';
import { SERVER_API_URL } from '../../app.constants';
import { createRequestOption } from '../../shared/util/request-util';
import { Moment } from 'moment';

type EntityResponseType = HttpResponse<IGruppe>;
type EntityArrayResponseType = HttpResponse<IGruppe[]>;

@Injectable({ providedIn: 'root' })
export class GruppenService {
  public resourceUrl = SERVER_API_URL + 'api/gruppen';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGruppe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  findByRoomAndDate(roomId: number, date: moment.Moment): Observable<EntityArrayResponseType> {
    const formattedDate = this.formatDate(date);
    return this.http.get<IGruppe[]>(`${this.resourceUrl}/${formattedDate}/raum/${roomId}`, { observe: 'response' });
  }

  private formatDate(moment?: Moment): string {
    return moment && moment.isValid() ? moment.format(DATE_FORMAT) : '';
  }
}
