import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WwHackathonTeam1TestModule } from '../../../test.module';
import { BuchungDetailComponent } from 'app/entities/buchung/buchung-detail.component';
import { Buchung } from 'app/shared/model/buchung.model';

describe('Component Tests', () => {
  describe('Buchung Management Detail Component', () => {
    let comp: BuchungDetailComponent;
    let fixture: ComponentFixture<BuchungDetailComponent>;
    const route = ({ data: of({ buchung: new Buchung(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WwHackathonTeam1TestModule],
        declarations: [BuchungDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BuchungDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BuchungDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load buchung on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.buchung).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
