import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class WarrantyAbsentService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAbsentData(pageindex,pagesize){
    return this.http.post(this.api.BASEURL+"/searchnocertsituresult",{pageindex:pageindex,pagesize:pagesize,matlcode:null});
  }
  download(){
    return this.http.post(this.api.BASEURL+"/searchnocertsituexcel",{matlcode:null});
  }
}
