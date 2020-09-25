import { Route } from '@angular/router';

import { BookRoomComponent } from './book-room.component';

export const bookingRoute: Route = {
  path: 'booking',
  component: BookRoomComponent,
  outlet: 'booking'
};
