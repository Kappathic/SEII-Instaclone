import {Component, DoCheck, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { SnackBarService } from '../snack-bar-service.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {B64toImgService} from '../b64to-img.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements DoCheck, OnInit{
  isLoggedIn = false;
  currentUserName: any;
  searchToken: any;
  filteredUsers: any;

  constructor(
    private snackBar: SnackBarService,
    private http: HttpClient,
    public router: Router,
    public b64toImg: B64toImgService
    ) {}

  submitLogout(): void {
    const requestUrl = 'api/auth/logout';
    this.http.post(requestUrl, {
    }).subscribe(
      (data: any) => {
        console.log('successfully Logged out!');
        this.snackBar.open('Successfully Logged Out!', 'Close');
        localStorage.removeItem('currentUser');
        this.isLoggedIn = false;
        this.router.navigate(['login']);
      },
      (error) => {
        this.snackBar.open('Something went wrong!', 'Close');
      }
    );
  }
  submitSearch(): void{
    if (!this.searchToken || this.searchToken.trim() === '') {this.filteredUsers = []; return;  }
    const requestUrl = 'api/user';
    this.http.get(requestUrl, {
    }).subscribe(
      (data: any) => {
        this.filterUsers(data);
      },
      (error) => {
        switch (error.status) {
          case 401:
            console.log('Wrong credentials');
            this.snackBar.open('Wrong credentials', 'close');
            break;
          case 404:
            console.log('No search results!');
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
  filterUsers(allUsers: any): void {
    this.filteredUsers = [];
    for (const user of allUsers) {
      if (user.username.toLowerCase().includes(this.searchToken.toLowerCase()) && this.filteredUsers.length < 8) {
        // @ts-ignore
        this.filteredUsers.push(user);
      }
    }
  }
  ngDoCheck(): void {
    this.router.onSameUrlNavigation = 'reload';
    if (localStorage.getItem('currentUser')){
      this.isLoggedIn = true;
      this.currentUserName = localStorage.getItem('currentUser');
    }
  }

  ngOnInit(): void {
    this.filterUsers([]);
  }
}
