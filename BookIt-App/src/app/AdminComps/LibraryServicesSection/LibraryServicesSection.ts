import { Component } from '@angular/core';
import {AdminService} from '../../Service/admin.service';
import {Subscription} from 'rxjs';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-LibraryServicesSection',
  imports: [
    FormsModule
  ],
  templateUrl: './LibraryServicesSection.html',
  styleUrl: './LibraryServicesSection.css'
})
export class LibraryServicesSection {
  bookSn1: number=0
  userId1: number=0
  bookSn2: number=0
  userId2: number=0
  sub1?: Subscription;
  sub2?: Subscription;
  sub3?: Subscription;
  removelId1: number=0;
  constructor(private httpAdminService:AdminService) {
  }
  assignBook():any{
    this.sub1=this.httpAdminService.assignBook$(this.userId1, this.bookSn1).subscribe(
      (error:any) => {console.log(error);}
    )
  }
  submitBook():any{
    this.sub2=this.httpAdminService.submitBook$( this.userId2,this.bookSn2).subscribe(
      (error:any) => {console.log(error);}
    )
  }
  removeUser():any{
    this.sub3=this.httpAdminService.removeUser$(this.removelId1).subscribe(
      (error:any) => {console.log(error);}
    )
  }
  ngOnDestroy() {
    this.sub2?.unsubscribe();
    this.sub1?.unsubscribe();
  }
}
