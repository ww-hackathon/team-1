import { Moment } from 'moment';
import { IRaum } from 'app/shared/model/raum.model';
import { IGruppe } from 'app/shared/model/gruppe.model';
import { IUser } from 'app/core/user/user.model';

export interface IBuchung {
  id?: number;
  datum?: Moment;
  user?: IUser;
  gruppe?: IGruppe;
  raum?: IRaum;
}

export class Buchung implements IBuchung {
  constructor(public id?: number, public datum?: Moment, public user?: IUser, public gruppe?: IGruppe, public raum?: IRaum) {}
}
