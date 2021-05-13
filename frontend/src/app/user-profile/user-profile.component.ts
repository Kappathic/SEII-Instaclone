import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {SnackBarService} from '../snack-bar-service.service';
import {SafeResourceUrl} from '@angular/platform-browser';
import {B64toImgService} from '../b64to-img.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  isFollowed = false;
  username: any;
  biography: any;
  profilePic: any;
  userPosts: any;
  postCount: any;
  followerCount: any;

  constructor(
    private router: Router,
    private http: HttpClient,
    private snackBar: SnackBarService,
    private b64toImg: B64toImgService ) { }
  getProfilePosts(): void{
    let requestUrl = 'api/user/profile';
    requestUrl = requestUrl + '/' + localStorage.getItem('currentUser');
    this.http.get(requestUrl, {}).subscribe(
      (data: any) => {
        this.username = data.username;
        this.biography = data.description;
        this.userPosts = data.posts;
        this.postCount = data.posts.length;
        this.followerCount = data.follower.length;
        this.profilePic = this.b64toImg.convert(data.profilePic);
        this.snackBar.open('Profile Content Loaded!', 'close');
        console.log(data);
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
  convertPostItem(postData: any): SafeResourceUrl{
    return this.b64toImg.convert(postData);
  }

  ngOnInit(): void {
    this.getProfilePosts();
  }
}
