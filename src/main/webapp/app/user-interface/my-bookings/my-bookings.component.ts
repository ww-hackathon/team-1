import { Component, OnInit } from '@angular/core';
import { IBuchung } from 'app/shared/model/buchung.model';
import { AccountService } from 'app/core/auth/account.service';
import { UserService } from 'app/core/user/user.service';
import { BuchungService } from 'app/entities/buchung/buchung.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss'],
})
export class MyBookingsComponent implements OnInit {
  buchungen: IBuchung[] = [];

  constructor(private accountService: AccountService, private userService: UserService, private buchungService: BuchungService) {}

  ngOnInit(): void {
    this.accountService.getAuthenticationState().subscribe(account => {
      if (account) {
        this.userService
          .find(account.login)
          .subscribe(user =>
            this.buchungService.findByUserd(user.id).subscribe((res: HttpResponse<IBuchung[]>) => (this.buchungen = res.body || []))
          );
      }
    });
  }
}
