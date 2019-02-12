import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class WarehouseEntryNoticeService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getReport(codedmarking,year,month,matlcode){
    return this.http.post(this.api.BASEURL+"/searchbyprodnocache",{codedmarking:codedmarking,year:year,month:month,matlcode:matlcode})
  }
  getSignImage(username){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username}).subscribe(result=>{
        resolve(result);
      })
    })
  }
  getMatlTypeByCodedmarking(codedmarking){
    return this.http.post(`${this.api.BASEURL}/searchtypebycodedmarking`,{codedmarking:codedmarking});
  }
  getMatlTypeByMatlcode(matlcode){
    return this.http.post(`${this.api.BASEURL}/searchtypebymatlcode`,{matlcode:matlcode});
  }
}
