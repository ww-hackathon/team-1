import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'buchung',
        loadChildren: () => import('./buchung/buchung.module').then(m => m.WwHackathonTeam1BuchungModule),
      },
      {
        path: 'raum',
        loadChildren: () => import('./raum/raum.module').then(m => m.WwHackathonTeam1RaumModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class WwHackathonTeam1EntityModule {}
