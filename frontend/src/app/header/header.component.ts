import {Component, DoCheck, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { SnackBarService } from '../snack-bar-service.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements DoCheck{
  isLoggedIn = false;
  constructor(
    private snackBar: SnackBarService,
    private http: HttpClient,
    private router: Router
  ) {}

  submitLogout(): void {
    const requestUrl = 'api/auth/logout';
    localStorage.removeItem('currentUser');
    this.isLoggedIn = false;
    this.http.post(requestUrl, {
    }).subscribe(
      (data: any) => {
        if (data) {
          console.log(data);
          console.log('successfully Logged out!');
          this.snackBar.open('Successfully Logged Out!', 'Close');
          this.router.navigate(['home']);
        }else{
          this.snackBar.open('Something went wrong!', 'Close');
        }
      },
      (error) => {
          this.snackBar.open('Something went wrong!', 'Close');
      }
    );
  }


  ngDoCheck(): void {
    this.router.onSameUrlNavigation = 'reload';
    if (localStorage.getItem('currentUser')){
      this.isLoggedIn = true;
    }
  }

}
