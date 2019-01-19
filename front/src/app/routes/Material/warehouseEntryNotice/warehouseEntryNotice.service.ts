import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class WarehouseEntryNoticeService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getReport(codedmarking,year,month,matlcode){
    return this.http.post(this.api.BASEURL+"/getRegisterReport",{codedmarking:codedmarking,year:year,month:month,matlcode:matlcode})
  }
  getSignImage(username){
    return this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username})
  }
}
