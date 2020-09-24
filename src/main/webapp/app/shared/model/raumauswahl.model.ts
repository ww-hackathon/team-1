export interface IRaumauswahl {
  haeuser?: string[];
  riegel?: string[];
  stockwerke?: string[];
}

export class Raumauswahl implements IRaumauswahl {
  constructor(public haeuser?: string[], public riegel?: string[], public stockwerke?: string[]) {}
}
