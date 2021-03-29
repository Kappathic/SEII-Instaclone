import { Component, OnInit } from '@angular/core';
import {SnackBarService} from "../snack-bar-service.service";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
    hide = true;
    username: any;
    password: any;
    constructor(
    private router: Router,
    private http: HttpClient,
    private cookieService: CookieService,
    private snackBar: SnackBarService) { }

  // Sends login Request to login API endpoint via JSON file - password hashing is done on backend side
  // If Login is successful, a local Cookie is generated to start the User Session. (valid for 30 minutes)
  // We are aware that this is by far not a safe method to organize user-sessions, but with this project we did not focus on security.
  // Depending on the Status callback, the user gets notified if his credentials are wrong or if his username does not exist.
  submitLogin(): void {
    const requestUrl = 'http://localhost:5000/login';
    this.http.post(requestUrl, {
      username: this.username,
      password: this.password
    }).subscribe(
      (data: any) => {
        console.log(data);
      },
      (error) => {
        switch (error.status) {
          case 200:
            console.log('successfully Logged in!');
            this.snackBar.open('Successfully Logged In!', 'close');
            // Creating a SessionId cookie that validates the user Session - Expires in 30 minutes (1/48 of a Day)
            this.cookieService.set('sessionId', this.username, {path: '/', expires: 1 / 48});
            this.router.navigate(['home']);
            break;
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
  ngOnInit(): void {
  }
}