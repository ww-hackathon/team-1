import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WwHackathonTeam1SharedModule } from 'app/shared/shared.module';
import { BuchungComponent } from './buchung.component';
import { BuchungDetailComponent } from './buchung-detail.component';
import { BuchungUpdateComponent } from './buchung-update.component';
import { BuchungDeleteDialogComponent } from './buchung-delete-dialog.component';
import { buchungRoute } from './buchung.route';

@NgModule({
  imports: [WwHackathonTeam1SharedModule, RouterModule.forChild(buchungRoute)],
  declarations: [BuchungComponent, BuchungDetailComponent, BuchungUpdateComponent, BuchungDeleteDialogComponent],
  entryComponents: [BuchungDeleteDialogComponent],
})
export class WwHackathonTeam1BuchungModule {}
