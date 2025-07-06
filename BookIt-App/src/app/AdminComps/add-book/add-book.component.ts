import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Book} from '../../model/book';
import {Subscription} from 'rxjs';
import {AdminService} from '../../Service/admin.service';

@Component({
  selector: 'app-add-book',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './add-book.component.html',
  styleUrl: './add-book.component.css'
})
export class AddBookComponent {
  book: Book={
    title:'',
    author:'',
    category:'',
    quantity:0,
  }
  sub?:Subscription;
  constructor(private httpAdminServices:AdminService) {
  }
  onSubmit() {
    const newBook={...(this.book)};
    this.sub=this.httpAdminServices.addBook$(newBook).subscribe(
      (error:any) => {console.log(error);},
    )
  }
  ngOnDestroy() {
    this.sub?.unsubscribe();
  }

}
