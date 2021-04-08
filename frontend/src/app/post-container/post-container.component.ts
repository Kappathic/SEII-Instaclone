import { Component, OnInit } from '@angular/core';
import {SnackBarService} from '../snack-bar-service.service';

@Component({
  selector: 'app-post-container',
  templateUrl: './post-container.component.html',
  styleUrls: ['./post-container.component.scss']
})
export class PostContainerComponent implements OnInit {
  public isActive = true;
  constructor(
    private snackBar: SnackBarService
  ) {}
  ngOnInit(): void {
  }
  test(): void{
    this.snackBar.open('SnackBar Works!', 'close');
  }
}
