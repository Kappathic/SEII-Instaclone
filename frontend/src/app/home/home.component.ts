import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {SnackBarService} from '../snack-bar-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  mainFeedPosts: any;

  constructor(
    private router: Router,
    private http: HttpClient,
    private snackBar: SnackBarService) {
  }

  loadPosts(): void {
    const requestUrl = 'api/post/feed';
    this.http.get(requestUrl, {}).subscribe(
      (data: any) => {
      console.log(data);
      this.snackBar.open('Mainfeed loaded.', 'close');
      this.mainFeedPosts = data;
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
  isLoggedIn(): boolean {
    return !(localStorage.getItem('currentUser') == null);
  }
  ngOnInit(): void {
    if (this.isLoggedIn()) {
      this.loadPosts();
    }
  }
}
