import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { WwHackathonTeam1SharedModule } from 'app/shared/shared.module';

import { DocsComponent } from './docs.component';

import { docsRoute } from './docs.route';

@NgModule({
  imports: [WwHackathonTeam1SharedModule, RouterModule.forChild([docsRoute])],
  declarations: [DocsComponent],
})
export class DocsModule {}
