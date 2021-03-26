import { Component, OnInit } from '@angular/core';
import { SnackBarService } from '../snack-bar-service.service';
import {CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  constructor(
    private snackBar: SnackBarService,
    private cookieService: CookieService) { }
  openSnack(): void{
    this.snackBar.open('SnackBar Works!', 'close');
  }
  doLogin(): void{
    this.cookieService.set('sessionId', 'testUser', {path: '/', expires: 1 / 48});
    this.snackBar.open('logged In!', 'close');
  }
  doLogout(): void{
    this.cookieService.delete('sessionId');
    this.snackBar.open('logged Out!', 'close');
  }
  ngOnInit(): void {
  }
}
