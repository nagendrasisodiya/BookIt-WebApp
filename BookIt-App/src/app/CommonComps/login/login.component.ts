import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Login} from '../../model/login';
import {LoginService} from '../../Service/login.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  login:Login={
    email:'',
    password:'',
  }

  constructor(
    private loginService: LoginService,
    private router: Router
  ) {}
  onSubmit() {
    if (this.login.email && this.login.password) {
      const credentials = { ...(this.login) };
      this.loginService.login$(credentials).subscribe({
        next: (response) => {
          console.log('Login response:', response);
          if (this.loginService.isAuthenticated()) {
            this.router.navigate(['/main/profile']);
          }
        },
        error: (error) => {
          console.error('Login failed:', error);
        }
      });
    }
  }
  navigateToRegistration() {
      this.router.navigate(['/register']);
  }
}
