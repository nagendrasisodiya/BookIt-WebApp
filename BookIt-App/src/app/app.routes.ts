import { Routes } from '@angular/router';
import {ProfileComponent} from './CommonComps/profile/profile.component';
import {BooksComponent} from './CommonComps/books/books.component';
import {DashBoardComponent} from './UserComps/dash-board/dash-board.component';
import {AddBookComponent} from './AdminComps/add-book/add-book.component';
import {UsersComponent} from './AdminComps/users/users.component';
import {MainCompComponent} from './main-comp/main-comp.component';
import {LoginComponent} from './CommonComps/login/login.component';
import {LibraryServicesSection} from './AdminComps/LibraryServicesSection/LibraryServicesSection';
import {AssignedBooksComponent} from './AdminComps/assigned-books/assigned-books.component';
import {RegistrationComponent} from './UserComps/registration/registration.component';
import {BookRequestComponent} from './UserComps/book-request/book-request.component';
import {RemoteBookRequestsComponent} from './AdminComps/remote-book-requests/remote-book-requests.component';

export const routes: Routes =  [
  {path: '', component: LoginComponent},
  {path: 'register', component:RegistrationComponent},
  {path:'main', component: MainCompComponent,
    children:[
      {path: 'profile', component: ProfileComponent},
      {path: 'dashboard', component: DashBoardComponent},
      {path: 'books', component: BooksComponent},
      {path: 'addBooks', component: AddBookComponent},
      {path: 'users', component: UsersComponent},
      {path:'assign$Submit', component:LibraryServicesSection},
      {path:'assignedBooks', component:AssignedBooksComponent},
      {path:'requestbook', component: BookRequestComponent},
      {path:'remoteRequestbook', component: RemoteBookRequestsComponent},
    ]
  },
];
