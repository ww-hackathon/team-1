import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { bookingRoute } from './user-interface/book-room/book-room.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { Authority } from 'app/shared/constants/authority.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { BookRoomComponent } from './user-interface/book-room/book-room.component';
import { SearchRoomComponent } from './user-interface/search-room/search-room.component';
import { MyBookingsComponent } from './user-interface/my-bookings/my-bookings.component';

const LAYOUT_ROUTES = [navbarRoute, bookingRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          data: {
            authorities: [Authority.ADMIN],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
        },
        {
          path: 'account',
          loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
        },
        {
          path: 'booking',
          component: BookRoomComponent,
        },
        {
          path: 'searching',
          component: SearchRoomComponent,
        },
        {
          path: 'my-bookings',
          data: {
            authorities: [Authority.USER],
          },
          canActivate: [UserRouteAccessService],
          component: MyBookingsComponent,
        },
        ...LAYOUT_ROUTES,
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    ),
  ],
  exports: [RouterModule],
})
export class WwHackathonTeam1AppRoutingModule {}
