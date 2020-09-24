import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { IGruppe } from '../../shared/model/gruppe.model';

@Component({
  selector: 'jhi-gruppe',
  templateUrl: './gruppe.component.html',
})
export class GruppeComponent implements OnInit {
  gruppen?: IGruppe[];
  eventSubscriber?: Subscription;

  constructor() {}

  loadAll(): void {
    // Aufruf alle Gruppen zu laden
  }

  ngOnInit(): void {
    this.loadAll();
    const gruppe = {
      id: 1,
      name: 'Gruppe1',
      spaces: 2,
    };
    this.gruppen = [gruppe];
  }

  trackId(index: number, item: IGruppe): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }
}
