import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import { BuchungComponent } from 'app/entities/buchung/buchung.component';
import { RaumService } from 'app/entities/raum/raum.service';
import { IRaumauswahl, Raumauswahl } from 'app/shared/model/raumauswahl.model';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'jhi-search-room',
  templateUrl: './search-room.component.html',
  styleUrls: ['./search-room.component.scss']
})
export class SearchRoomComponent implements OnInit {

  raumauswahl: Raumauswahl = {};

  constructor(private raumService: RaumService) { 
  
  }
  ngOnInit(): void {}

  loadData(): void {
    console.log();
    this.raumService.raumauswahl().subscribe((res: HttpResponse<IRaumauswahl>) => (this.raumauswahl = res.body || []));
  }
  
  /* myControl = new FormControl();
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
