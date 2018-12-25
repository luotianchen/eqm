import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";
import {filter} from "rxjs/internal/operators";

@Injectable()
export class UserService {

  constructor( private http: HttpClient,private api:ApiService) {
  }

  getUsers(){
    return this.http.get(this.api.BASEURL+"/getuserform");
  }

  getRoles(){
    return this.http.get(this.api.BASEURL+"/getrole");
  }

  editName(username,newname){
    return this.http.post(this.api.BASEURL+"/changename",{username:username,newname:newname})
  }

  editEmail(username,newemail){
    return this.http.post(this.api.BASEURL+"/changeemail",{username:username,email:newemail})
  }

  editNote(username,newnote){
    return this.http.post(this.api.BASEURL+"/changenote",{username:username,note:newnote})
  }
  getSignImage(username){
    return this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username})
  }
  uploadImg(formData){
    const req = new HttpRequest('POST', this.api.BASEURL+'/putsignature' , formData, {
      // reportProgress: true
    });
    return this.http
      .request(req)
      .pipe(filter(e => e instanceof HttpResponse));
  }
  resetPSW(username){
    return this.http.post(this.api.BASEURL+"/resetpassword",{username:username});
  }
  deleteUser(username){
    return this.http.post(this.api.BASEURL+"/deleteuser",{username:username});
  }
  setRoles(username,role,role2,role3,role4,role5){
    return this.http.post(this.api.BASEURL+"/changerole",{username:username,role:role,role2:role2,role3:role3,role4:role4,role5:role5});
  }
  addUser(username,name,role,role2,role3,role4,role5){
    return this.http.post(this.api.BASEURL+"/putuser",{username:username,name:name,role:role,role2:role2,role3:role3,role4:role4,role5:role5})
  }
}
