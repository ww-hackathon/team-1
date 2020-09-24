import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { IGruppe } from '../../shared/model/gruppe.model';
import { GruppenService } from './gruppe.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-gruppe',
  templateUrl: './gruppe.component.html',
})
export class GruppeComponent implements OnInit {
  gruppen?: IGruppe[];
  eventSubscriber?: Subscription;

  constructor(private gruppenSerive: GruppenService) {}

  loadAll(): void {
    this.gruppenSerive.query().subscribe((res: HttpResponse<IGruppe[]>) => (this.gruppen = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IGruppe): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }
}
