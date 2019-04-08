import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class BarometricReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  searchbyprodno(prodno){
    return this.http.post(`${this.api.BASEURL}`+"/searchbyprodno",{prodno:prodno,status:-1})
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getprenotiformreport" , formData, { responseType: 'arraybuffer' });
  }
  searchchanneldata(dwgno){
    return this.http.post(this.api.BASEURL+"/searchchanneldata",{dwgno:dwgno,status:1})
  }
}

