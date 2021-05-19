import { Component, OnInit, Input } from '@angular/core';
import {SnackBarService} from '../snack-bar-service.service';
import { B64toImgService } from '../b64to-img.service';
import {HttpClient} from '@angular/common/http';
import has = Reflect.has;

@Component({
  selector: 'app-post-container',
  templateUrl: './post-container.component.html',
  styleUrls: ['./post-container.component.scss']
})
export class PostContainerComponent implements OnInit {
  // tslint:disable-next-line:no-input-rename
  @Input('username') username: any | 'Kappathic';
  @Input('image') postImageB64: any;
  @Input('description') postDescription: any;
  @Input('hashtags') hashtags: any;
  @Input('comments') comments: any; // TODO check JSON file for correct name
  @Input('id') postId: any; // TODO add on every instance of post container
  newComment: any;
  postImage: any;
  constructor(
    private snackBar: SnackBarService,
    private B64toImg: B64toImgService,
    private http: HttpClient
  ) {}
  isActive = true;
  isLiked = false;
  ngOnInit(): void {
    this.postImage = this.B64toImg.convert(this.postImageB64);
    for (let tag = 0; tag < this.hashtags.length; tag++){
      if (this.hashtags[tag][0] !== '#'){
        this.hashtags[tag] = '   #' + this.hashtags[tag];
      }
    }
  }
  changeLike(): void {
    if (!this.isLiked){
      const requestUrl = 'api/post/like/' + this.postId.toString();
      this.http.post(requestUrl, {
      }).subscribe(
        (data: any) => {
          console.log('like successful');
          this.snackBar.open('Successfully liked!', 'close');
          this.newComment = null;
        },
        (error) => {
          switch (error.status) {
            case 200:
              console.log('liked successful');
              this.snackBar.open('Successfully liked!', 'close');
              this.isLiked = false;
              break;
            default:
              console.log('Bad Request');
              this.snackBar.open('Bad Request', 'close');
              break;
          }
        }
      );
    }
    else{
      const requestUrl = 'api/post/revokelike/' + this.postId.toString();
      this.http.post(requestUrl, {
      }).subscribe(
        (data: any) => {
          console.log('like revoked successful');
          this.snackBar.open('Successfully revoked liked!', 'close');
          this.newComment = null;
        },
        (error) => {
          switch (error.status) {
            case 200:
              console.log('revoked liked successful');
              this.snackBar.open('Successfully revoked liked!', 'close');
              this.isLiked = false;
              break;
            default:
              console.log('Bad Request');
              this.snackBar.open('Bad Request', 'close');
              break;
          }
        }
      );
      // would like to implement but backend ain't capable of offering a method to revoke likes on posts
    }
  }
  saveComment(): void {
      if (this.newComment) {
      const requestUrl = 'api/post/comment/' + this.postId.toString();
      this.http.post(requestUrl, {
        text: this.newComment,
        userId: localStorage.getItem('currentUserID')
      }).subscribe(
        (data: any) => {
          console.log('comment successful');
          this.snackBar.open('Successfully commented!', 'close');
          this.newComment = null;
        },
        (error) => {
          switch (error.status) {
            case 200:
              console.log('comment successful');
              this.snackBar.open('Successfully commented!', 'close');
              break;
            default:
              console.log('Bad Request');
              this.snackBar.open('Bad Request', 'close');
              break;
          }
        }
      );
    } else {
      this.snackBar.open('No comment written', 'close');
    }

  }
}
