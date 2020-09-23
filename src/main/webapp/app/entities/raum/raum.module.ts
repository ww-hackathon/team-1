import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WwHackathonTeam1SharedModule } from 'app/shared/shared.module';
import { RaumComponent } from './raum.component';
import { RaumDetailComponent } from './raum-detail.component';
import { RaumUpdateComponent } from './raum-update.component';
import { RaumDeleteDialogComponent } from './raum-delete-dialog.component';
import { raumRoute } from './raum.route';

@NgModule({
  imports: [WwHackathonTeam1SharedModule, RouterModule.forChild(raumRoute)],
  declarations: [RaumComponent, RaumDetailComponent, RaumUpdateComponent, RaumDeleteDialogComponent],
  entryComponents: [RaumDeleteDialogComponent],
})
export class WwHackathonTeam1RaumModule {}
