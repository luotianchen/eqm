import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MeasuringInstrumentLedgerService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getGaugename(){
    return this.http.get(this.api.BASEURL+"/getdevicename");
  }
  getMillunit(){
    return this.http.get(this.api.BASEURL+"/getputmaterial");
  }
  getManaglevel(){
    return this.http.get(this.api.BASEURL+"/getkind");
  }
  getCircle(gaugename,managlevel){
    return this.http.post(this.api.BASEURL+"/searchtimefordevicename",{gaugename:gaugename,managlevel:managlevel});
  }
  getUsers(){
    return this.http.get(this.api.BASEURL+"/getuserform")
  }
  putMeasuringInstrumentLedger(data){
    return this.http.post(this.api.BASEURL+"/putpregaumeatable",data);
  }
  getInfo(gaugeno){
    return this.http.post(this.api.BASEURL+"/searchpregaubystatus",{gaugeno:gaugeno,audit:-1});
  }
}
