import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRaum } from 'app/shared/model/raum.model';
import { RaumService } from './raum.service';
import { RaumDeleteDialogComponent } from './raum-delete-dialog.component';

@Component({
  selector: 'jhi-raum',
  templateUrl: './raum.component.html',
})
export class RaumComponent implements OnInit, OnDestroy {
  raums?: IRaum[];
  eventSubscriber?: Subscription;

  constructor(protected raumService: RaumService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.raumService.query().subscribe((res: HttpResponse<IRaum[]>) => (this.raums = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRaums();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRaum): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRaums(): void {
    this.eventSubscriber = this.eventManager.subscribe('raumListModification', () => this.loadAll());
  }

  delete(raum: IRaum): void {
    const modalRef = this.modalService.open(RaumDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.raum = raum;
  }
}
