import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {LegalDisclosureComponent} from './legal-disclosure/legal-disclosure.component';
import {PrivacyNoticeComponent} from './privacy-notice/privacy-notice.component';
import {AuthGuardService} from './auth-guard.service';
import {AddPostComponent} from './add-post/add-post.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {ProfileSettingsComponent} from './profile-settings/profile-settings.component';

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
    path: 'profile/:id',
    component: UserProfileComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'legal-disclosure',
    component: LegalDisclosureComponent
  },
  {
    path: 'privacy-notice',
    component: PrivacyNoticeComponent,
  },
  {
    path: 'add-post',
    component: AddPostComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'settings/:id',
    component: ProfileSettingsComponent,
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
    anchorScrolling: 'enabled',
    onSameUrlNavigation: 'reload'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor() {
  }
}
