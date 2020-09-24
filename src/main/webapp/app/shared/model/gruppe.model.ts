export interface IGruppe {
  id: number;
  name?: string;
  anzahlPlaetze?: number;
}

export class Gruppe implements IGruppe {
  constructor(public id: number, public name?: string, public anzahlPlaetze?: number) {}
}
