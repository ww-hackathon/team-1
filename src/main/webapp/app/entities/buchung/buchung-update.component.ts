import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBuchung, Buchung } from 'app/shared/model/buchung.model';
import { BuchungService } from './buchung.service';
import { IRaum } from 'app/shared/model/raum.model';
import { RaumService } from 'app/entities/raum/raum.service';

@Component({
  selector: 'jhi-buchung-update',
  templateUrl: './buchung-update.component.html',
})
export class BuchungUpdateComponent implements OnInit {
  isSaving = false;
  raums: IRaum[] = [];
  datumDp: any;

  editForm = this.fb.group({
    id: [],
    datum: [],
    user: [],
    gruppe: [],
    raum: [],
  });

  constructor(
    protected buchungService: BuchungService,
    protected raumService: RaumService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ buchung }) => {
      this.updateForm(buchung);

      this.raumService.query().subscribe((res: HttpResponse<IRaum[]>) => (this.raums = res.body || []));
    });
  }

  updateForm(buchung: IBuchung): void {
    this.editForm.patchValue({
      id: buchung.id,
      datum: buchung.datum,
      user: buchung.user,
      gruppe: buchung.gruppe,
      raum: buchung.raum,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const buchung = this.createFromForm();
    if (buchung.id !== undefined) {
      this.subscribeToSaveResponse(this.buchungService.update(buchung));
    } else {
      this.subscribeToSaveResponse(this.buchungService.create(buchung));
    }
  }

  private createFromForm(): IBuchung {
    return {
      ...new Buchung(),
      id: this.editForm.get(['id'])!.value,
      datum: this.editForm.get(['datum'])!.value,
      user: this.editForm.get(['user'])!.value,
      gruppe: this.editForm.get(['gruppe'])!.value,
      raum: this.editForm.get(['raum'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBuchung>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRaum): any {
    return item.id;
  }
}
