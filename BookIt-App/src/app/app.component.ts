import { Component } from '@angular/core';

import {RouterOutlet} from '@angular/router';
import {NgClass} from '@angular/common';
import {SideBarComponent} from './CommonComps/side-bar/side-bar.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'LibraryManagmentSystem-App';
  sidebarCollapsed = false;

  toggleSidebar() {
    this.sidebarCollapsed = !this.sidebarCollapsed;
  }
}
