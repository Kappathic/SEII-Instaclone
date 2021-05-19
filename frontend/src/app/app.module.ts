import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MatSliderModule } from '@angular/material/slider';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SnackBarService } from './snack-bar-service.service';
import { LegalDisclosureComponent } from './legal-disclosure/legal-disclosure.component';
import { PrivacyNoticeComponent } from './privacy-notice/privacy-notice.component';
import { AddPostComponent } from './add-post/add-post.component';
import {FormsModule} from '@angular/forms';
import { PostContainerComponent } from './post-container/post-container.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import {ImageCropperModule} from 'ngx-image-cropper';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    PageNotFoundComponent,
    HeaderComponent,
    FooterComponent,
    LegalDisclosureComponent,
    PrivacyNoticeComponent,
    PostContainerComponent,
    AddPostComponent,
    UserProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatRippleModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSnackBarModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ImageCropperModule,
    MatListModule,
  ],
  providers: [
    SnackBarService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
