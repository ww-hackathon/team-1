import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRaum } from 'app/shared/model/raum.model';
import { RaumService } from './raum.service';

@Component({
  templateUrl: './raum-delete-dialog.component.html',
})
export class RaumDeleteDialogComponent {
  raum?: IRaum;

  constructor(protected raumService: RaumService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.raumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('raumListModification');
      this.activeModal.close();
    });
  }
}
