import { Component, OnInit, Input } from '@angular/core';
import {SnackBarService} from '../snack-bar-service.service';
import { B64toImgService } from '../b64to-img.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-post-container',
  templateUrl: './post-container.component.html',
  styleUrls: ['./post-container.component.scss']
})
export class PostContainerComponent implements OnInit {
  // tslint:disable-next-line:no-input-rename
  @Input('post') post: any;
  //
  newComment: any;
  postImage: any;
  isActive = true;
  isLiked = false;
  showComments = false;
  private postImageB64: any;
  public postDescription: any;
  public hashtags: any;
  public comments: any;
  public likeCount: any;
  public username: any;
  private postId: any;
  constructor(
    private snackBar: SnackBarService,
    private B64toImg: B64toImgService,
    private http: HttpClient
  ) {}
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
      if (!this.newComment) { this.snackBar.open('No comment written', 'close'); return; }
      const requestUrl = 'api/post/comment/' + this.postId.toString();
      this.http.post(requestUrl, {
        text: this.newComment,
        id: localStorage.getItem('currentUserID')
      }).subscribe(
        (data: any) => {
          console.log('comment successful');
          console.log(data.comments);
          this.comments = this.addCommentsUserName(data.comments);
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
  }
  addCommentsUserName(comments: any): any{
    console.log(comments);
    if (comments.length === 0) { return comments; }
    for (let i  = 0; i < comments.length; i++ ) {
      const requestUrl = 'api/user/' + comments[i].userId;
      console.log(requestUrl);
      this.http.get(requestUrl, {}).subscribe(
        (data: any) => {
          comments[i].username = data.username;
        },
        (error) => {
          comments[i].username = 'notFound';
        }
      );
    }
    return comments;
  }
  ngOnInit(): void {
    this.postImageB64 = this.post.image;
    this.postDescription = this.post.description;
    this.hashtags = this.post.hashtags;
    this.comments = this.addCommentsUserName(this.post.comments);
    this.likeCount = this.post.likesUserId.length;
    this.postId = this.post.id;
    this.postImage = this.B64toImg.convert(this.postImageB64);
    for (let tag = 0; tag < this.hashtags.length; tag++){
      if (this.hashtags[tag][0] !== '#'){
        this.hashtags[tag] = '   #' + this.hashtags[tag];
      }
    }
  }
}
