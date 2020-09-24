import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import './vendor';
import { WwHackathonTeam1SharedModule } from 'app/shared/shared.module';
import { WwHackathonTeam1CoreModule } from 'app/core/core.module';
import { WwHackathonTeam1AppRoutingModule } from './app-routing.module';
import { WwHackathonTeam1HomeModule } from './home/home.module';
import { WwHackathonTeam1EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { BookRoomComponent } from './user-interface/book-room/book-room.component';
import { SearchRoomComponent } from './user-interface/search-room/search-room.component';
import { MyBookingsComponent } from './user-interface/my-bookings/my-bookings.component';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    WwHackathonTeam1SharedModule,
    WwHackathonTeam1CoreModule,
    WwHackathonTeam1HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    WwHackathonTeam1EntityModule,
    WwHackathonTeam1AppRoutingModule,
  ],
  declarations: [
    MainComponent,
    NavbarComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent,
    BookRoomComponent,
    SearchRoomComponent,
    MyBookingsComponent,
  ],
  bootstrap: [MainComponent],
})
export class WwHackathonTeam1AppModule {}
