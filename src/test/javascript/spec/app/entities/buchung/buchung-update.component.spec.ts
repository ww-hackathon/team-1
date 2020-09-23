import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WwHackathonTeam1TestModule } from '../../../test.module';
import { BuchungUpdateComponent } from 'app/entities/buchung/buchung-update.component';
import { BuchungService } from 'app/entities/buchung/buchung.service';
import { Buchung } from 'app/shared/model/buchung.model';

describe('Component Tests', () => {
  describe('Buchung Management Update Component', () => {
    let comp: BuchungUpdateComponent;
    let fixture: ComponentFixture<BuchungUpdateComponent>;
    let service: BuchungService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WwHackathonTeam1TestModule],
        declarations: [BuchungUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BuchungUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BuchungUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BuchungService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Buchung(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Buchung();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
