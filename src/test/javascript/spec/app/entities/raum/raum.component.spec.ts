import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { WwHackathonTeam1TestModule } from '../../../test.module';
import { RaumComponent } from 'app/entities/raum/raum.component';
import { RaumService } from 'app/entities/raum/raum.service';
import { Raum } from 'app/shared/model/raum.model';

describe('Component Tests', () => {
  describe('Raum Management Component', () => {
    let comp: RaumComponent;
    let fixture: ComponentFixture<RaumComponent>;
    let service: RaumService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WwHackathonTeam1TestModule],
        declarations: [RaumComponent],
      })
        .overrideTemplate(RaumComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RaumComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RaumService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Raum(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.raums && comp.raums[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
