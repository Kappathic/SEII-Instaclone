import { Injectable } from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class B64toImgService {

  constructor(
    private sanitizer: DomSanitizer
  ) { }
  convert(data: any): SafeResourceUrl{
    return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpeg;base64,` + data);
  }
}
