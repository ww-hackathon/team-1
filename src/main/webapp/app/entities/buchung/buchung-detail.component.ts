import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBuchung } from 'app/shared/model/buchung.model';

@Component({
  selector: 'jhi-buchung-detail',
  templateUrl: './buchung-detail.component.html',
})
export class BuchungDetailComponent implements OnInit {
  buchung: IBuchung | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ buchung }) => (this.buchung = buchung));
  }

  previousState(): void {
    window.history.back();
  }
}
