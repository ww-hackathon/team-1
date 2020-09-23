import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBuchung, Buchung } from 'app/shared/model/buchung.model';
import { BuchungService } from './buchung.service';
import { BuchungComponent } from './buchung.component';
import { BuchungDetailComponent } from './buchung-detail.component';
import { BuchungUpdateComponent } from './buchung-update.component';

@Injectable({ providedIn: 'root' })
export class BuchungResolve implements Resolve<IBuchung> {
  constructor(private service: BuchungService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBuchung> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((buchung: HttpResponse<Buchung>) => {
          if (buchung.body) {
            return of(buchung.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Buchung());
  }
}

export const buchungRoute: Routes = [
  {
    path: '',
    component: BuchungComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wwHackathonTeam1App.buchung.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BuchungDetailComponent,
    resolve: {
      buchung: BuchungResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wwHackathonTeam1App.buchung.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BuchungUpdateComponent,
    resolve: {
      buchung: BuchungResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wwHackathonTeam1App.buchung.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BuchungUpdateComponent,
    resolve: {
      buchung: BuchungResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wwHackathonTeam1App.buchung.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
