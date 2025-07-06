import { Component } from '@angular/core';
import {User} from '../../model/user';
import {Observable, Subscription} from 'rxjs';
import {NgForOf} from '@angular/common';
import {AdminService} from '../../Service/admin.service';
@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  userData:Array<User>=[]
  sub1?: Subscription;
  constructor(private httpAdminService:AdminService ) {
  }
  ngOnInit() {
    this.getAllUsers()
  }
  getAllUsers():any{
    this.sub1=this.httpAdminService.getAllUser$().subscribe(
      (response:any) => {this.userData=response},
      (error:any)=> {console.log(error)}
    );
    return this.userData;
  }
  ngOnDestroy() {
    this.sub1?.unsubscribe();
  }

}
