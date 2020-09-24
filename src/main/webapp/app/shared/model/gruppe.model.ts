export interface IGruppe {
  id: number;
  name?: string;
  spaces?: number;
}

export class Gruppe implements IGruppe {
  constructor(public id: number, public name?: string, public spaces?: number) {}
}
