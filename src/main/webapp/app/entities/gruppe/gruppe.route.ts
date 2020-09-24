import { Routes } from '@angular/router';
import { GruppeComponent } from './gruppe.component';

import { Authority } from '../../shared/constants/authority.constants';
import { UserRouteAccessService } from '../../core/auth/user-route-access-service';

export const gruppenRoute: Routes = [
  {
    path: '',
    component: GruppeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wwHackathonTeam1App.raum.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
