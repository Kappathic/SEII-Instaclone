import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {SnackBarService} from '../snack-bar-service.service';
import {ImageCroppedEvent} from 'ngx-image-cropper';
import {B64toImgService} from '../b64to-img.service';

@Component({
  selector: 'app-profile-settings',
  templateUrl: './profile-settings.component.html',
  styleUrls: ['./profile-settings.component.scss']
})
export class ProfileSettingsComponent implements OnInit {
  username: any;
  bio: any;
  bioBackup: any;
  pictureBackup: any;
  email: any;
  picture: any;
  imageChangedEvent: any = '';
  croppedImage: any;
  bColor = '#56CCF2';

  constructor(
    private router: Router,
    private http: HttpClient,
    private route: ActivatedRoute,
    private b64toImg: B64toImgService,
    private snackBar: SnackBarService) {
  }

  patchProfile(): void { // TODO test if backend allows to patch the user otherwhise just change profilepic
    if (localStorage.getItem('currentUser') !== this.username){ this.router.navigate(['home']); return; }
    if (!this.picture){ this.picture = this.pictureBackup; }
    if (!this.bio){ this.bio = this.bioBackup; }
    if (!this.email){ this.snackBar.open('Please enter email!', 'close'); return; }
    const requestUrl = 'api/user';
    this.http.patch(requestUrl, {
      picture: this.picture.split(',')[1],
      email: this.email,
      description: this.bio
    }).subscribe(
      (data: any) => {
        console.log('successfully registered!');
        this.snackBar.open('Successfully registered!', 'close');
        this.router.navigate(['profile/' + localStorage.getItem('currentUser')]);
      },
      (error) => {
        switch (error.status) {
          case 200:
            console.log('successfully changed profile');
            this.snackBar.open('Successfully changed profile!', 'close');
            break;
          default:
            console.log('Bad Request');
            this.snackBar.open('Bad Request', 'close');
            break;
        }
      }
    );
  }

  changeProfilePic(): void {
    if (localStorage.getItem('currentUser') !== this.username){ this.router.navigate(['home']); return; }
    if (!this.picture){ this.snackBar.open('Please choose a picture to change!', 'Close'); return; }
    const requestUrl = 'api/user/updateProfilePic';
    this.http.post(requestUrl, {
      picture: this.picture.split(',')[1]
    }).subscribe(
      (data: any) => {
        console.log('successfully registered!');
        this.snackBar.open('Successfully registered!', 'close');
        this.router.navigate(['profile/' + localStorage.getItem('currentUser')]);
      },
      (error) => {
        switch (error.status) {
          case 200:
            console.log('successfully changed profilepic');
            this.snackBar.open('Successfully changed profile-picture!', 'close');
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

  setValue(): void{
    if (localStorage.getItem('currentUser') !== this.username){ this.router.navigate(['home']); return; }
    const requestUrl = 'api/user/profile/' + this.username;
    this.http.get(requestUrl, {}).subscribe(
        (data: any) => {
          this.bioBackup = data.description;
          this.pictureBackup = this.b64toImg.convert(data.profilePic);
          this.snackBar.open('Profile Content Loaded!', 'close');
        },
        (error) => {
          switch (error.status) {
            case 401:
              console.log('Wrong credentials');
              this.snackBar.open('Wrong credentials', 'close');
              break;
            case 404:
              console.log('User not Found!');
              this.snackBar.open('User not Found', 'close');
              break;
            default:
              console.log('Bad Request');
              this.snackBar.open('Bad Request', 'close');
              break;
          }
        }
      );
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.username = params.id;
    });
    this.setValue();
  }
}
