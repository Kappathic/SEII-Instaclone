import { Component, OnInit } from '@angular/core';
import { SnackBarService } from '../snack-bar-service.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
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
        console.log(data);
        console.log('successfully Logged out!');
        this.snackBar.open('Successfully Logged Out!', 'Close');
        this.router.navigate(['home']);
      },
      (error) => {
          this.snackBar.open('Something went wrong!', 'Close');
      }
    );
  }
  ngOnInit(): void {
  }
}
