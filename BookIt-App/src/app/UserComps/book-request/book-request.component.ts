import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {BookRequest} from '../../model/book-request';
import {Subscription} from 'rxjs';
import {UserService} from '../../Service/user.service';

@Component({
  selector: 'app-book-request',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './book-request.component.html',
  styleUrl: './book-request.component.css'
})
export class BookRequestComponent {
  bookRequest:BookRequest={
    title:"",
    author:"",
    address:""
  }
  sub?:Subscription;
  constructor(private httpUserServices:UserService) {}

  onSubmit() {
    const request={...(this.bookRequest)};
    this.sub=this.httpUserServices.requestBook$(request).subscribe(
      (error:any) => {console.log(error);},
    )
  }
}
