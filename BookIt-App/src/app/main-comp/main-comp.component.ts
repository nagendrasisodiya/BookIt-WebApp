import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {SideBarComponent} from "../CommonComps/side-bar/side-bar.component";

@Component({
  selector: 'app-main-comp',
  standalone: true,
    imports: [
        RouterOutlet,
        SideBarComponent
    ],
  templateUrl: './main-comp.component.html',
  styleUrl: './main-comp.component.css'
})
export class MainCompComponent {

}
