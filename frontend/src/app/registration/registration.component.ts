import { Component, OnInit } from '@angular/core';
import {SnackBarService} from "../snack-bar-service.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  hide1 = true;
  hide2 = true;
  picture: any;
  username: any;
  prename: any;
  name: any;
  mail: any;
  password: any;
  confirmPassword: any;
  bio: any;

  constructor(
    private http: HttpClient,
    private snackBar: SnackBarService) {
  }

  // Sends register Request to login API endpoint via JSON file - password hashing is done on backend side
  // Status Callbacks trigger a Snackbar pop up on the bottom of the Screen to let the user know if his registration was successful
  registerUser(): void {
    if (this.password === this.confirmPassword) {
      const requestUrl = 'api/auth/register';
      this.http.post(requestUrl, {
        username: this.username,
        mail: this.mail,
        password: this.password,
        description: this.bio
      }).subscribe(
        (data: any) => {
        },
        (error) => {
          switch (error.status) {
            case 200:
              console.log('successfully registered!');
              this.snackBar.open('Successfully registered!', 'close');
              break;
            case 400:
              console.log('User already exists!');
              this.snackBar.open('User already exists!', 'close');
              break;
            default:
              console.log('Bad Request');
              this.snackBar.open('Bad Request', 'close');
              break;
          }
        }
      );
    } else {
      this.snackBar.open('Passwords do not match!', 'close');
    }
  }

  ngOnInit(): void {
  }
}
