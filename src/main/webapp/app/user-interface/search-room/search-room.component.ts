import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { RaumService } from 'app/entities/raum/raum.service';
import { Raumauswahl, IRaumauswahl } from 'app/shared/model/raumauswahl.model';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-search-room',
  templateUrl: './search-room.component.html',
  styleUrls: ['./search-room.component.scss'],
})
export class SearchRoomComponent implements OnInit {
  raumauswahl: Raumauswahl = {};
  selectedHaus = '';
  selectedStockwerk = '';
  selectedRiegel = '';
  date: Date = new Date();

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
        date: this.date,
      },
    });
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