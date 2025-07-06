import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {Book} from '../model/book';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private http: HttpClient) {
  }
  getProfile$(): Observable<User> {
    return this.http.get<any>('http://localhost:8080/common/getProfile');
  }
  getBooks$(): Observable<Book[]> {
    return this.http.get<Book[]>(`http://localhost:8080/common/getAllBooks`);
  }

}
