import {Component, DoCheck } from '@angular/core';
import { SnackBarService } from '../snack-bar-service.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements DoCheck {
  isLoggedIn = false;
  temp: string | null = localStorage.getItem('currentUser');
  constructor(
    private snackBar: SnackBarService,
    private http: HttpClient,
    private router: Router
  ) {}
  
  submitLogout(): void {
    const requestUrl = 'api/auth/logout';
    this.http.post(requestUrl, {
    }).subscribe(
      (data: any) => {
        if (data.status === 200) {
          localStorage.removeItem('username');
          console.log(data);
          console.log('successfully Logged out!');
          this.snackBar.open('Successfully Logged Out!', 'Close');
          this.router.navigate(['home']);
        }
        else{
          this.snackBar.open('Something went wrong!', 'Close');
        }
      },
      (error) => {
          this.snackBar.open('Something went wrong!', 'Close');
      }
    );
  }
  
  ngDoCheck(): void {
    if (this.temp){
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  }
}