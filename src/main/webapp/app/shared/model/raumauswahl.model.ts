export interface IRaumauswahl {
  haeuser?: string[];
  riegel?: string[];
  stockwerk?: string[];
}

export class Raumauswahl implements IRaumauswahl {
  constructor(public haeuser?: string[], public riegel?: string[], public stockwerk?: string[]) {}
}
