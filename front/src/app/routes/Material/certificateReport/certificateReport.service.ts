import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class CertificateReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getcodedmarking(){
    return this.http.get(this.api.BASEURL+"/getcodedmarking");
  }
  getmaterial(codedmarking){
    return this.http.post(this.api.BASEURL+"/getmaterial",{codedmarking:codedmarking});
  }
  getlogo(){
    return this.http.get(`${this.api.BASEURL}/getlogo`);
  }
  getNameByUserName(username){
    return this.http.post(`${this.api.BASEURL}/getname`,{username:username});
  }
}

