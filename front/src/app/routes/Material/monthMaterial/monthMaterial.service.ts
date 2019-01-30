import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MonthMaterialService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getMonthData(pageindex,pagesize,matlcode,inyear,inmonth){
    return this.http.post(this.api.BASEURL+"/searchmonthmatlresult",{pageindex:pageindex,pagesize:pagesize,matlcode:matlcode,inyear:inyear,inmonth:inmonth});
  }
  download(matlcode,inyear,inmonth){
    return this.http.post(this.api.BASEURL+"/searchmonthmatlexcel",{matlcode:matlcode,inyear:inyear,inmonth:inmonth},{responseType:"arraybuffer"})
  }
}
