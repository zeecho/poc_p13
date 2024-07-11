import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  login(credentials: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/login`, credentials);
  }

  register(user: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/register`, user);
  }
}
