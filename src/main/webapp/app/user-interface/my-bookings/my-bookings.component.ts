import { Component, OnInit } from '@angular/core';
import { Buchung } from 'app/shared/model/buchung.model';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss'],
})
export class MyBookingsComponent implements OnInit {
  buchungen: Buchung[] = [
    {
      id: 1,
      user: {
        firstName: 'Test',
        id: 'testid',
      },
      gruppe: {
        id: 1,
        anzahlPlaetze: 2,
        name: 'Gruppe 1',
      },
      raum: {
        haus: '1',
        id: 2,
        riegel: 'A',
        stockwerk: '2.OG',
      },
    },
    {
      id: 2,
      user: {
        firstName: 'Test2',
        id: 'testid2',
      },
      gruppe: {
        id: 3,
        anzahlPlaetze: 3,
        name: 'Gruppe 5',
      },
      raum: {
        haus: '2',
        id: 4,
        riegel: 'B',
        stockwerk: '2.OG',
      },
    },
  ];

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    const account: Observable<Account | null> = this.accountService.identity();
  }
}
