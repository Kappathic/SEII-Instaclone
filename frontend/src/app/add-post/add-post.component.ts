import {Component, OnInit} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {HttpClient} from '@angular/common/http';
import {SnackBarService} from '../snack-bar-service.service';
import {Router} from '@angular/router';
import {ImageCroppedEvent, ImageCropperModule} from 'ngx-image-cropper';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit {
  picture: any;
  description: any;
  hashtags: any;
  imageChangedEvent: any = '';
  croppedImage: any;
  bColor = '#56CCF2';
  constructor(
    private router: Router,
    private http: HttpClient,
    private cookieService: CookieService,
    private snackBar: SnackBarService
  ){}

   addPost(): void{
     if (this.picture){
       const requestUrl = 'api/post';
       this.http.post(requestUrl, {
         image: this.picture.split(',')[1],
         description: this.description,
         hashtags: [this.hashtags]
       }).subscribe(
         (data: any) => {
           console.log('Post successful!');
           this.snackBar.open('Post successful!', 'close');
         },
         (error) => {
           switch (error.status) {
             case 400:
               console.log('Something went wrong posting your picture please try again later!');
               this.snackBar.open('Something went wrong posting your picture please try again later!', 'close');
               break;
             default:
               console.log('Bad Request');
               this.snackBar.open('Bad Request', 'close');
               break;
           }
         }

       );
     } else {
       this.snackBar.open('No picture to post found!', 'close');
     }
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
    this.snackBar.open('you can now crop image!', 'close');
  }
  loadImageFailed(): void {
    this.snackBar.open('An Error occurred while loading your picture!', 'close');
  }
  ngOnInit(): void {
  }
}
