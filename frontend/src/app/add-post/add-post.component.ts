import {Component, OnInit} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {HttpClient} from '@angular/common/http';
import {SnackBarService} from '../snack-bar-service.service';


@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit{

  picture: any;
  description: any;
  constructor(
    private cookieService: CookieService,
    private http: HttpClient,
    private snackBar: SnackBarService
  ){}

  checkLogin(): boolean{
    return this.cookieService.check('sessionId');
  }

  onPictureChange(event: any): void{
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();

      reader.onload = (event: ProgressEvent) => {
        this.picture = (event.target as FileReader).result;
      };

      reader.readAsDataURL(event.target.files[0]);
    }
  }



   addPost(): void{
     if (this.picture){
       const requestUrl = 'http://localhost:5000/post';
       this.http.post(requestUrl, {
       }).subscribe(
         (data: any) => {
         },
         (error) => {
           switch (error.status) {
             case 200:
               console.log('Post successful!');
               this.snackBar.open('Post successful!', 'close');
               break;
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

  ngOnInit(): void {
  }

}
