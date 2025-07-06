import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Login} from '../model/login';
import {Router} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';
import {BehaviorSubject, Observable, tap} from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private currentUserSubject = new BehaviorSubject<any>(null);
  constructor(
    private http: HttpClient,
    private router: Router,
    public jwtHelper: JwtHelperService
  ) {
  }

  login$(login: Login): Observable<any> {
    return this.http.post<any>('http://localhost:8080/auth/login', login)
      .pipe(
        tap(response => {
          if (response.token) {
            localStorage.setItem('token', response.token);
            const decodedToken = this.decodeToken(response.token);
            this.currentUserSubject.next(decodedToken);
          }
        }),
      );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
    this.router.navigate(['/']);
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token && !this.jwtHelper.isTokenExpired(token);
  }

  private decodeToken(token: string): any {
    const payload = token.split('.')[1];
    const decoded: any = JSON.parse(atob(payload));
    return decoded;
  }

  isAdmin$(): boolean {
    const token = localStorage.getItem('token');
    if (!token) {
      return false;
    }
    return this.decodeToken(token).roles.some(
      (role: { authority: string }) => role.authority === 'ROLE_ADMIN')
  }

  isUser$(): boolean {
    const token = localStorage.getItem('token');
    if (!token) {
      return false;
    }
    return this.decodeToken(token).roles.some(
      (role: { authority: string }) => role.authority === 'ROLE_USER')
  }

}
