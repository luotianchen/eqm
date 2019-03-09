import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class WeldingRecordService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getWeldingRecord(prodno){
    return this.http.post(`${this.api.BASEURL}/searchweldingrecord`,{prodno:prodno})
  }
  getUserNames(){
    return this.http.get(this.api.BASEURL+'/getuserform');
  }
  getdistribute(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1})
  }
  putWeldingRecord(data){
    return this.http.post(this.api.BASEURL+"/putweldingrecord",data)
  }
}
