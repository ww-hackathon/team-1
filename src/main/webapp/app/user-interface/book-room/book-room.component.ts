import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { RaumService } from 'app/entities/raum/raum.service';
import { HttpResponse } from '@angular/common/http';
import { GruppenService } from 'app/entities/gruppe/gruppe.service';
import { BuchungService } from 'app/entities/buchung/buchung.service';
import { Buchung, IBuchung } from 'app/shared/model/buchung.model';
import { IGruppe, Gruppe } from 'app/shared/model/gruppe.model';
import { IUser } from 'app/core/user/user.model';
import { AccountService } from 'app/core/auth/account.service';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-book-room',
  templateUrl: './book-room.component.html',
  styleUrls: ['./book-room.component.scss'],
})
export class BookRoomComponent implements OnInit {
  myControl = new FormControl();
  options: string[] = ['One', 'Two', 'Three'];
  filteredOptions: Observable<string[]> | undefined;
  data: any = {};
  routeState: any;
  roomId = 0;
  buchungen: IBuchung[] = [];
  gruppen: IGruppe[] = [];
  user: IUser = {};
  selectedGruppe: Gruppe = { id: 0 };

  constructor(
    private accountService: AccountService,
    private userService: UserService,
    private router: Router,
    private raumService: RaumService,
    private gruppenService: GruppenService,
    private buchungService: BuchungService
  ) {
    if (this.router.getCurrentNavigation()?.extras.state) {
      this.routeState = this.router.getCurrentNavigation()?.extras.state;
      if (this.routeState) {
        this.data.haus = this.routeState.haus ? this.routeState.haus : '';
        this.data.stock = this.routeState.stock ? this.routeState.stock : '';
        this.data.riegel = this.routeState.riegel ? this.routeState.riegel : '';
        this.data.date = this.routeState.date ? this.routeState.date : '';
      }
    }
    this.getUser();
  }
  ngOnInit(): void {
    this.raumService
      .findbyProperties(this.data.haus, this.data.riegel, this.data.stockwerk)
      .subscribe((res: HttpResponse<number>) => (this.roomId = res.body || 0));
    this.buchungService
      .findByRoomAndDate(this.roomId, this.data.date)
      .subscribe((res: HttpResponse<IBuchung[]>) => (this.buchungen = res.body || []));
    this.gruppenService
      .findByRoomAndDate(this.roomId, this.data.date)
      .subscribe((res: HttpResponse<IGruppe[]>) => (this.gruppen = res.body || []));
    if (this.user) {
      this.getUser();
    }
  }

  getUser(): void {
    this.accountService.getAuthenticationState().subscribe(account => {
      if (account) {
        this.userService.find(account.login).subscribe(user => (this.user = user));
      }
    });
  }

  /*ngOnInit(): void {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startsWith(''),
      map(value => this._filter(value))
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }*/
}
