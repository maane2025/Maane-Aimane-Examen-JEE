import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

const AUTH_API = `${environment.apiBaseUrl}/auth/`;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string, roles?: string[]): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      roles
    }, httpOptions);
  }

  // Store token in local storage after successful login
  storeToken(token: string): void {
    localStorage.setItem('auth-token', token);
  }

  // Get token from local storage
  getToken(): string | null {
    return localStorage.getItem('auth-token');
  }

  // Remove token on logout
  logout(): void {
    localStorage.removeItem('auth-token');
    localStorage.removeItem('user');
  }

  // Store user details
  storeUser(user: any): void {
    localStorage.setItem('user', JSON.stringify(user));
  }

  // Get stored user
  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  // Check if user is logged in
  isLoggedIn(): boolean {
    return !!this.getToken();
  }
} 