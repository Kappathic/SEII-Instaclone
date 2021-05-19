import { Component, OnInit } from '@angular/core';
import {SnackBarService} from '../snack-bar-service.service';
import {HttpClient} from '@angular/common/http';
import {ImageCroppedEvent} from 'ngx-image-cropper';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  hide1 = true;
  hide2 = true;
  picture: any;
  username: any;
  prename: any;
  name: any;
  email: any;
  password: any;
  confirmPassword: any;
  bio: any;
  imageChangedEvent: any = '';
  croppedImage: any;
  bColor = '#56CCF2';

  constructor(
    private router: Router,
    private http: HttpClient,
    private snackBar: SnackBarService) {
  }

  // Sends register Request to login API endpoint via JSON file - password hashing is done on backend side
  // Status Callbacks trigger a Snackbar pop up on the bottom of the Screen to let the user know if his registration was successful
  registerUser(): void {
    if (!this.picture) { this.snackBar.open('Please upload a profile picture!', 'close'); }
    if (!this.username) { this.snackBar.open('Please enter a username!', 'close'); }
    if (!this.email) {this.snackBar.open('Please enter an email!', 'close'); }
    if (this.password !== this.confirmPassword) { this.snackBar.open('Passwords do not match!', 'close'); }
    const requestUrl = 'api/auth/register';
    this.http.post(requestUrl, {
      username: this.username,
      email: this.email,
      password: this.password,
      description: this.bio,
      profilePic: this.picture.split(',')[1]
    }).subscribe(
      (data: any) => {
          console.log('successfully registered!');
          this.snackBar.open('Successfully registered!', 'close');
          this.router.navigate(['login']);
      },
    (error) => {
      switch (error.status) {
        case 200:
          console.log('successfully registered!');
          this.snackBar.open('Successfully registered!', 'close');
          break;
        case 400:
          console.log('User already exists!');
          this.snackBar.open('User already exists!', 'close');
          break;
        default:
          console.log('Bad Request');
          this.snackBar.open('Bad Request', 'close');
          break;
        }
      }
    );
  }
  fileChangeEvent(event: any): void {
    this.picture = event;
    this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent): void {
    this.picture = event.base64;
  }
  imageLoaded(image: HTMLImageElement): void {
    // show cropper
  }
  cropperReady(): void {
    // cropper ready
    this.snackBar.open('You can now crop your image!', 'close');
  }
  loadImageFailed(): void {
    this.snackBar.open('An Error occurred while loading your picture!', 'close');
  }

  ngOnInit(): void {
  }
}
