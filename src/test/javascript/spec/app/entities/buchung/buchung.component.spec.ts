import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { WwHackathonTeam1TestModule } from '../../../test.module';
import { BuchungComponent } from 'app/entities/buchung/buchung.component';
import { BuchungService } from 'app/entities/buchung/buchung.service';
import { Buchung } from 'app/shared/model/buchung.model';

describe('Component Tests', () => {
  describe('Buchung Management Component', () => {
    let comp: BuchungComponent;
    let fixture: ComponentFixture<BuchungComponent>;
    let service: BuchungService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WwHackathonTeam1TestModule],
        declarations: [BuchungComponent],
      })
        .overrideTemplate(BuchungComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BuchungComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BuchungService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Buchung(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.buchungs && comp.buchungs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
