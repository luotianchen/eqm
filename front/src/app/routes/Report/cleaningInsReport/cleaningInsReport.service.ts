import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class CleaningInsReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  searchbyprodno(prodno){
    return this.http.post(`${this.api.BASEURL}`+"/searchbyprodno",{prodno:prodno,status:-1})
  }
  searchchanneldata(dwgno){
    return this.http.post(this.api.BASEURL+"/searchchanneldata",{dwgno:dwgno,status:1})
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getcleanlinessreport",formData, { responseType: 'arraybuffer' })
  }
  getuserform() {
    return this.http.get(`${this.api.BASEURL}/getuserform`);
  }
}

