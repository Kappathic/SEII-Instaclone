import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {SnackBarService} from '../snack-bar-service.service';
import {SafeResourceUrl} from '@angular/platform-browser';
import {B64toImgService} from '../b64to-img.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  isFollowed = false;
  ownProfile = false;
  userId: any;
  username: any;
  biography: any;
  profilePic: any;
  userPosts: any;
  postCount: any;
  followerCount: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private http: HttpClient,
    private snackBar: SnackBarService,
    private b64toImg: B64toImgService ) { }
  getProfilePosts(username: string): void{
    const requestUrl = 'api/user/profile/' + username;

    this.http.get(requestUrl, {}).subscribe(
      (data: any) => {
        if (data.username === localStorage.getItem('currentUser')) { this.ownProfile = true; }
        this.userId = data.id;
        this.username = data.username;
        this.biography = data.description;
        this.userPosts = data.posts;
        this.postCount = data.posts.length;
        this.profilePic = this.b64toImg.convert(data.profilePic);
        this.followerCount = data.followerUserId.length;
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
  followUser(profileId: string): void{
    const requestUrl = 'api/user/follow/' + profileId;
    this.http.post(requestUrl, {
    }).subscribe(
      (data: any) => {
        console.log('You followed' + this.username + '!');
        this.isFollowed = true;
        this.followerCount ++;
        },
      (error) => {
        switch (error.status) {
          case 400:
            console.log('Something went wrong whilst following the desired user.');
            this.snackBar.open('Something went wrong whilst following the desired user.', 'close');
            break;
          default:
            console.log('Bad Request');
            this.snackBar.open('Bad Request', 'close');
            break;
        }
      }
    );
  }
  unfollowUser(profileId: string): void{
    const requestUrl = 'api/user/unfollow/' + profileId;
    this.http.post(requestUrl, {
    }).subscribe(
      (data: any) => {
        console.log('You unfollowed' + this.username + '!');
        this.isFollowed = false;
        this.followerCount --;
      },
      (error) => {
        switch (error.status) {
          case 400:
            console.log('Something went wrong whilst unfollowing the desired user.');
            this.snackBar.open('Something went wrong whilst unfollowing the desired user.', 'close');
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
    this.route.params.subscribe(params => {
      this.username = params.id;
    });
    this.getProfilePosts(this.username);
  }
}
