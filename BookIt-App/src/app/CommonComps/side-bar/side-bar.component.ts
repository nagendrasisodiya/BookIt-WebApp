import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {LoginService} from '../../Service/login.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [
    RouterLink,
    NgIf,
  ],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent {

  constructor(private loginService: LoginService) {
  }
  isAdmin(): boolean {
    return this.loginService.isAdmin$();
  }
  isUser(): boolean {
    return this.loginService.isUser$();
  }
  showLibraryServices = false;

  toggleLibraryServices() {
    this.showLibraryServices = !this.showLibraryServices;
  }

}
