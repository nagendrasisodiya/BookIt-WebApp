import { Component } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {User} from '../../model/user';
import {CommonService} from '../../Service/common.service';
import {Subscription} from 'rxjs';
import {LoginService} from '../../Service/login.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [HttpClientModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  user: User={
    username:'',
    email:'',
    password:'',
    books:[],
  }
  subscription?: Subscription;
  constructor(private commonService: CommonService, private httpLoginService:LoginService) {

  }
  ngOnInit() {
    this.getUserProfile();
  }
  getUserProfile():any{
    this.subscription = this.commonService.getProfile$().subscribe({
      next: (response: User) => {
        this.user = response;
      },
      error: (error) => {
        console.error('Error fetching profile:', error);
      }
    });
  }
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }

  logeOut() {
    this.httpLoginService.logout();
  }
}
