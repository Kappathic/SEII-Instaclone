import {Component, DoCheck } from '@angular/core';
import { SnackBarService } from '../snack-bar-service.service';
import {CookieService} from 'ngx-cookie-service';

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
    private cookieService: CookieService) { }
  ngDoCheck(): void {
    if (this.temp){
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  }
}
