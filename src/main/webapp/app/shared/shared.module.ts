import { NgModule } from '@angular/core';
import { WwHackathonTeam1SharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { JhMaterialModule } from 'app/shared/material/jh-material.module';

@NgModule({
  imports: [WwHackathonTeam1SharedLibsModule, JhMaterialModule],
  declarations: [FindLanguageFromKeyPipe, AlertComponent, AlertErrorComponent, LoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [LoginModalComponent],
  exports: [
    WwHackathonTeam1SharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    JhMaterialModule,
  ],
})
export class WwHackathonTeam1SharedModule {}
