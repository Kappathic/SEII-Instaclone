import { Component, OnInit, Input } from '@angular/core';
import {SnackBarService} from '../snack-bar-service.service';
import { B64toImgService } from '../b64to-img.service';
import has = Reflect.has;

@Component({
  selector: 'app-post-container',
  templateUrl: './post-container.component.html',
  styleUrls: ['./post-container.component.scss']
})
export class PostContainerComponent implements OnInit {
  // tslint:disable-next-line:no-input-rename
  @Input('username') username: any | 'Kappathic';
  @Input('image') postImageB64: any;
  @Input('description') postDescription: any;
  @Input('hashtags') hashtags: any;
  postImage: any;
  constructor(
    private snackBar: SnackBarService,
    private B64toImg: B64toImgService
  ) {}
  isActive = true;
  isLiked = false;
  ngOnInit(): void {
    this.postImage = this.B64toImg.convert(this.postImageB64);
    for (let tag = 0; tag < this.hashtags.length; tag++){
      if (this.hashtags[tag][0] !== '#'){
        this.hashtags[tag] = '   #' + this.hashtags[tag];
      }
    }
  }
}
