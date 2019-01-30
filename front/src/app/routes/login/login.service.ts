import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ApiService} from "../../core/api/api.service";

@Injectable()
export class LoginService {
  constructor( private http: HttpClient,private apiService:ApiService) {
  }
  login(username: string, password: string) {
    return new Promise((resolve, reject) => {
      this.http.post(this.apiService.BASEURL+'/login',
        {username: username, password: password}).subscribe(result=>{
          resolve(result);
      },err=>{
          reject(err);
      })
    });
  }

}
