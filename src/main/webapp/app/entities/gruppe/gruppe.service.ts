import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGruppe } from '../../shared/model/gruppe.model';
import { SERVER_API_URL } from '../../app.constants';
import { createRequestOption } from '../../shared/util/request-util';

type EntityArrayResponseType = HttpResponse<IGruppe[]>;

@Injectable({ providedIn: 'root' })
export class GruppenService {
  public resourceUrl = SERVER_API_URL + 'api/gruppen';

  constructor(protected http: HttpClient) {}

  //findByRoomIdAndDate

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGruppe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
