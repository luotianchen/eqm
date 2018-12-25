import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";
import {filter} from "rxjs/internal/operators";

@Injectable()
export class ProfileService {
  constructor( private http: HttpClient,private apiService:ApiService) {
  }
  getDepartment(role){
    return this.http.post(this.apiService.BASEURL+"/searchbyrole",{rolename:role});
  }
  getSignImage(username){
    return this.http.post(this.apiService.BASEURL+"/getsignatureurl",{username:username})
  }
  editName(username,newname){
    return this.http.post(this.apiService.BASEURL+"/changename",{username:username,newname:newname})
  }
  editEmail(username,email){
    return this.http.post(this.apiService.BASEURL+"/changename",{username:username,email:email})
  }
  uploadImg(formData){
    const req = new HttpRequest('POST', this.apiService.BASEURL+'/putsignature', formData, {
      // reportProgress: true
    });
    return this.http
      .request(req)
      .pipe(filter(e => e instanceof HttpResponse));

  }
}
