import {Component, OnInit} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {HttpClient} from '@angular/common/http';
import {SnackBarService} from '../snack-bar-service.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit {

  picture: any;
  description: any;
  hashtag: any;
  constructor(
    private router: Router,
    private http: HttpClient,
    private cookieService: CookieService,
    private snackBar: SnackBarService
  ){}

  checkLogin(): boolean{
      const temp: string | null = localStorage.getItem('currentUser');
      if (temp){
        return true;
      }else{
        return false;
      }
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
       const requestUrl = 'api/post';
       this.http.post(requestUrl, {
         image: this.picture,
         description: this.description,
         hashtag: this.hashtag
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

  ngOnInit(): void {
  }
}
