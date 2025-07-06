import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {Book} from '../model/book';
import {IssuedBook} from '../model/issued-book';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAllUser$(): Observable<User[]> {
    return this.http.get<User[]>(`http://localhost:8080/adminOnly/getAllUsers`);
  }
  addBook$(book: Book): Observable<any> {
    return this.http.post<Book>(`http://localhost:8080/adminOnly/addBook`, book);
  }

  assignBook$(userId: number, sNo: number): Observable<any> {
    return this.http.post(`http://localhost:8080/adminOnly/assignBook?userId=${userId}&bookId=${sNo}`
      ,null);
  }
  submitBook$(userId: number, sNo:number): Observable<any> {
    return this.http.post(`http://localhost:8080/adminOnly/submitBook?userId=${userId}&bookId=${sNo}`
      ,null);
  }
  removeUser$(userId:number): Observable<any> {
    return this.http.post(`http://localhost:8080/adminOnly/removeUser?userId=${userId}`, null);
  }
  getAssignedBooksWithDates$(): Observable<IssuedBook[]> {
    return this.http.get<IssuedBook[]>(`http://localhost:8080/adminOnly/assignedBooks`);
  }
  getRemoteBookRequests$(): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/adminOnly/getRemoteBookRequests`);
  }

  completeRemoteBookRequest$(requestId: number | undefined): Observable<any> {
    return this.http.post(`http://localhost:8080/adminOnly/completeRemoteBookRequest?requestId=${requestId}`, null);
  }
}
