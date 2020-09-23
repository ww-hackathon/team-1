import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRaum, Raum } from 'app/shared/model/raum.model';
import { RaumService } from './raum.service';

@Component({
  selector: 'jhi-raum-update',
  templateUrl: './raum-update.component.html',
})
export class RaumUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    haus: [],
    riegel: [],
    stockwerk: [],
  });

  constructor(protected raumService: RaumService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ raum }) => {
      this.updateForm(raum);
    });
  }

  updateForm(raum: IRaum): void {
    this.editForm.patchValue({
      id: raum.id,
      haus: raum.haus,
      riegel: raum.riegel,
      stockwerk: raum.stockwerk,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const raum = this.createFromForm();
    if (raum.id !== undefined) {
      this.subscribeToSaveResponse(this.raumService.update(raum));
    } else {
      this.subscribeToSaveResponse(this.raumService.create(raum));
    }
  }

  private createFromForm(): IRaum {
    return {
      ...new Raum(),
      id: this.editForm.get(['id'])!.value,
      haus: this.editForm.get(['haus'])!.value,
      riegel: this.editForm.get(['riegel'])!.value,
      stockwerk: this.editForm.get(['stockwerk'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRaum>>): void {
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
}
