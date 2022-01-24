import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const AuthUrl = 'http://localhost:8443/auth/';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLogin = false;

  role : string | undefined;

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {

    return this.http.post(AuthUrl + 'login', {
      username,
      password
    });

  }

  getRole() {
    return localStorage.getItem('ROLE')
  }

  isLoggedIn() {
    return localStorage.getItem('STATE')
  }

}
