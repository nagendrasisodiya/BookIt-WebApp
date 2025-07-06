import { Component } from '@angular/core';
import {DatePipe, NgForOf} from "@angular/common";
import {User} from '../../model/user';
import {Book} from '../../model/book';
import {AdminService} from '../../Service/admin.service';
import {Subscription} from 'rxjs';
import {IssuedBook} from '../../model/issued-book';

@Component({
  selector: 'app-assigned-books',
  imports: [
    NgForOf,
    DatePipe
  ],
  templateUrl: './assigned-books.component.html',
  styleUrl: './assigned-books.component.css'
})
export class AssignedBooksComponent {
  bookAssignments: IssuedBook[] = [];

  constructor(private httpAdminService: AdminService) {}
  sub?:Subscription;
  ngOnInit():void {
    this.getAssignedBooks()
  }
  getAssignedBooks(): void {
    this.sub = this.httpAdminService.getAssignedBooksWithDates$().subscribe(
      (response: IssuedBook[]) => { this.bookAssignments = response; },
      (error: any) => { console.log(error); }
    );
  }
  ngOnDestroy() {
    this.sub?.unsubscribe();
  }
}
