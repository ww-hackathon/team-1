export interface IRaum {
  id?: number;
  haus?: string;
  riegel?: string;
  stockwerk?: string;
}

export class Raum implements IRaum {
  constructor(public id?: number, public haus?: string, public riegel?: string, public stockwerk?: string) {}
}
