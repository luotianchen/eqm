import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class SingleWarrantyAbsentService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAbsentData(pageindex,pagesize,matlcode){
    return this.http.post(this.api.BASEURL+"/searchnocertsituresult",{pageindex:pageindex,pagesize:pagesize,matlcode:matlcode});
  }
  download(matlcode){
    return this.http.post(this.api.BASEURL+"/searchnocertsituexcel",{matlcode:matlcode},{responseType:"arraybuffer"});
  }
}
