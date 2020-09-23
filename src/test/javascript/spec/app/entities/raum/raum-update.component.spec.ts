import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WwHackathonTeam1TestModule } from '../../../test.module';
import { RaumUpdateComponent } from 'app/entities/raum/raum-update.component';
import { RaumService } from 'app/entities/raum/raum.service';
import { Raum } from 'app/shared/model/raum.model';

describe('Component Tests', () => {
  describe('Raum Management Update Component', () => {
    let comp: RaumUpdateComponent;
    let fixture: ComponentFixture<RaumUpdateComponent>;
    let service: RaumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WwHackathonTeam1TestModule],
        declarations: [RaumUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RaumUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RaumUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RaumService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Raum(123);
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
        const entity = new Raum();
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
