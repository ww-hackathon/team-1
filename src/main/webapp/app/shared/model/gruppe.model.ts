export interface IGruppe {
  id: number;
  name?: string;
  anzahlPlaetze?: number;
}

export interface GruppeDTO {
  gruppenList?: IGruppe[];
}

export class Gruppe implements IGruppe {
  constructor(public id: number, public name?: string, public anzahlPlaetze?: number) {}
}
