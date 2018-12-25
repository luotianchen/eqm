import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class ChangePasswordService {
  constructor( private http: HttpClient,private apiService:ApiService) {
  }
  changePassword(username,password,newpassword){
    return this.http.post(this.apiService.BASEURL+"/changepassword",{username:username,password:password,newpassword:newpassword})
  }
}
