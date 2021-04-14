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
  ngOnInit(): void {
  }
}
