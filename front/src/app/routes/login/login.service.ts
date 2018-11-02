import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class LoginService {
  constructor( private http: HttpClient) {
  }
  login(username: string, password: string) {
    return this.http.post('http://192.168.31.178:8080/login',
      {username: username, password: password});
  }

}
