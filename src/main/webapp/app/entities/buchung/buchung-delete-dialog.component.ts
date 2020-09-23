import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBuchung } from 'app/shared/model/buchung.model';
import { BuchungService } from './buchung.service';

@Component({
  templateUrl: './buchung-delete-dialog.component.html',
})
export class BuchungDeleteDialogComponent {
  buchung?: IBuchung;

  constructor(protected buchungService: BuchungService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.buchungService.delete(id).subscribe(() => {
      this.eventManager.broadcast('buchungListModification');
      this.activeModal.close();
    });
  }
}
