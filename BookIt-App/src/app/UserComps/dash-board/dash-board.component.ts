import { Component } from '@angular/core';
import {DatePipe, NgForOf} from '@angular/common';
import {Subscription} from 'rxjs';
import {UserService} from '../../Service/user.service';
import {IssuedBook} from '../../model/issued-book';

class UserServiceq {
}

@Component({
  selector: 'app-dash-board',
  imports: [
    NgForOf,
    DatePipe
  ],
  templateUrl: './dash-board.component.html',
  styleUrl: './dash-board.component.css'
})
export class DashBoardComponent {
    books:IssuedBook[]=[];
    sub?:Subscription;
  constructor(private httpUserService:UserService) {
    }
    ngOnInit() {
    this.getBookDetails()
    }
  getBookDetails(): void {
    this.sub = this.httpUserService.getBookDetails$().subscribe(
      (response: IssuedBook[]) => { this.books = response; },
      (error: any) => { console.log(error); }
    );
  }
  ngOnDestroy(): void {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }

}
