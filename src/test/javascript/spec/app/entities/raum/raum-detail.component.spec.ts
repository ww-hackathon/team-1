import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WwHackathonTeam1TestModule } from '../../../test.module';
import { RaumDetailComponent } from 'app/entities/raum/raum-detail.component';
import { Raum } from 'app/shared/model/raum.model';

describe('Component Tests', () => {
  describe('Raum Management Detail Component', () => {
    let comp: RaumDetailComponent;
    let fixture: ComponentFixture<RaumDetailComponent>;
    const route = ({ data: of({ raum: new Raum(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WwHackathonTeam1TestModule],
        declarations: [RaumDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RaumDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RaumDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load raum on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.raum).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
