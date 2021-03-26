import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {LegalDisclosureComponent} from './legal-disclosure/legal-disclosure.component';
import {PrivacyNoticeComponent} from './privacy-notice/privacy-notice.component';
import {AuthGuardService} from './auth-guard.service';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'legal-disclosure',
    component: LegalDisclosureComponent
  },
  {
    path: 'privacy-notice',
    component: PrivacyNoticeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: '',
    redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes, {
    useHash: false,
    anchorScrolling: 'enabled'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor() {
  }
}
