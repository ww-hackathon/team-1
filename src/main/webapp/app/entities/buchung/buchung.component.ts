import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBuchung } from 'app/shared/model/buchung.model';
import { BuchungService } from './buchung.service';
import { BuchungDeleteDialogComponent } from './buchung-delete-dialog.component';

@Component({
  selector: 'jhi-buchung',
  templateUrl: './buchung.component.html',
})
export class BuchungComponent implements OnInit, OnDestroy {
  buchungs?: IBuchung[];
  eventSubscriber?: Subscription;

  constructor(protected buchungService: BuchungService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.buchungService.query().subscribe((res: HttpResponse<IBuchung[]>) => (this.buchungs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBuchungs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBuchung): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBuchungs(): void {
    this.eventSubscriber = this.eventManager.subscribe('buchungListModification', () => this.loadAll());
  }

  delete(buchung: IBuchung): void {
    const modalRef = this.modalService.open(BuchungDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.buchung = buchung;
  }
}
