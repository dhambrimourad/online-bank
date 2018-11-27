import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs';

@Injectable()
export class LoginService {

  constructor (private http: Http) {}

  sendCredential(username: string, password: string) {
    const url = 'http://localhost:8080/index';
    const params = 'username=' + username + '&password=' + password;
    const headers = new Headers(
    {
      'Content-Type': 'application/x-www-form-urlencoded'
      // 'Access-Control-Allow-Credentials' : true
    });
    return this.http.post(url, params, {headers: headers, withCredentials : true});
  }

  logout() {
    const url = 'http://localhost:8080/logout';
    return this.http.get(url, { withCredentials: true });
  }

}
