import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class SingleWarrantyAbsentService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAbsentData(pageindex,pagesize,matlcode,designation){
    return this.http.post(this.api.BASEURL+"/searchnocertsituresult",{pageindex:pageindex,pagesize:pagesize,matlcode:matlcode,designation:designation});
  }
  download(matlcode,designation){
    return this.http.post(this.api.BASEURL+"/searchnocertsituexcel",{matlcode:matlcode,designation:designation},{responseType:"arraybuffer"});
  }
  getputmaterial() {
    return this.http.get(`${this.api.BASEURL}/getputmaterial`);
  }
}
