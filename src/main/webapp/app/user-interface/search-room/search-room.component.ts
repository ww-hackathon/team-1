import { Component, OnInit } from '@angular/core';
import { RaumService } from 'app/entities/raum/raum.service';
import { Raumauswahl, IRaumauswahl } from 'app/shared/model/raumauswahl.model';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MAT_MOMENT_DATE_FORMATS, MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';

import * as moment from 'moment';

@Component({
  selector: 'jhi-search-room',
  templateUrl: './search-room.component.html',
  styleUrls: ['./search-room.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS },
  ],
})
export class SearchRoomComponent implements OnInit {
  raumauswahl: Raumauswahl = {};
  selectedHaus = '';
  selectedStockwerk = '';
  selectedRiegel = '';
  formGroup = new FormGroup({
    date: new FormControl(moment()),
  });

  constructor(private raumService: RaumService, private router: Router) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.raumService.raumauswahl().subscribe((res: HttpResponse<IRaumauswahl>) => (this.raumauswahl = res.body || {}));
  }

  routeToBuchen(): void {
    this.router.navigate(['/booking'], {
      state: {
        haus: this.selectedHaus,
        stock: this.selectedStockwerk,
        riegel: this.selectedRiegel,
        date: this.formatDate(this.formGroup.get('date')?.value),
      },
    });
  }
  private formatDate(momentValue?: moment.Moment): string {
    return momentValue && momentValue.isValid() ? momentValue.format(DATE_FORMAT) : '';
  }

  /*
  myControl = new FormControl();
  options: string[] = ['One', 'Two', 'Three'];
  filteredOptions!: Observable<string[]>;
  ngOnInit(): void {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  } */
}
