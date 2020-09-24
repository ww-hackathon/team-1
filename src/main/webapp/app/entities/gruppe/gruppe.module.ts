import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { gruppenRoute } from './gruppe.route';
import { WwHackathonTeam1SharedModule } from 'app/shared/shared.module';
import { GruppeComponent } from './gruppe.component';

@NgModule({
  imports: [WwHackathonTeam1SharedModule, RouterModule.forChild(gruppenRoute)],
  declarations: [GruppeComponent],
  entryComponents: [],
})
export class WwHackathonTeam1GruppeModule {}
