import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MeasuringInstrumentLedgerService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getGaugename(){
    return this.http.get(this.api.BASEURL+"/getgaugename");
  }
  getMillunit(){
    return this.http.get(this.api.BASEURL+"/getputmaterial");
  }
  getManaglevel(){
    return this.http.get(this.api.BASEURL+"/getmanaglevel");
  }
  getCircle(gaugename,managlevel){
    return this.http.post(this.api.BASEURL+"/getcycle",{gaugename:gaugename,managlevel:managlevel});
  }
  getUsers(){
    return this.http.get(this.api.BASEURL+"/getuserform")
  }
  putMeasuringInstrumentLedger(data){
    return this.http.post(this.api.BASEURL+"/putmeasuringinstrumentledger",data);
  }
  getInfo(gaugeno){
    return this.http.post(this.api.BASEURL+"/getmeasuringinstrumentledger",{gaugeno:gaugeno,audit:-1});
  }
}
