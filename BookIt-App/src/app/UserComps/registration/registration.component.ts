import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NewUser} from '../../model/new-user';
import {UserService} from '../../Service/user.service';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  imports: [
    FormsModule
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
 newUser:NewUser={
   username:'',
   email:'',
   password:''
 };
 sub?:Subscription;
 constructor(private httpUserService:UserService, private router:Router) {}
  onSubmit() {
      const user = {...(this.newUser)};
      this.sub=this.httpUserService.regNewUser$(user).subscribe(
        (error:any) => {console.log(error)}
      )
    this.router.navigate(['']);
  }
}
