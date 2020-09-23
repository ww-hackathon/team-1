import { Moment } from 'moment';
import { IRaum } from 'app/shared/model/raum.model';

export interface IBuchung {
  id?: number;
  datum?: Moment;
  user?: string;
  gruppe?: string;
  raum?: IRaum;
}

export class Buchung implements IBuchung {
  constructor(public id?: number, public datum?: Moment, public user?: string, public gruppe?: string, public raum?: IRaum) {}
}
