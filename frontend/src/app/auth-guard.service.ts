import { Injectable } from '@angular/core';
import { Router} from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import {SnackBarService} from './snack-bar-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService{
  constructor(
    private cookieService: CookieService,
    public router: Router,
    private snackBar: SnackBarService) {  }
  // Checks if the User is logged in and therefore authenticated to access the route via a local userSession Cookie,
  // returns true if logged in.
  // Is used in the routing module via the canActivate Attribute
  canActivate(): boolean {
    if (!this.cookieService.check('sessionId')) {
      this.router.navigate(['login']);
      this.snackBar.open('You are not allowed to access this route. Please Log-In first.', 'Close');
      return false;
    }
    return true;
  }
}
