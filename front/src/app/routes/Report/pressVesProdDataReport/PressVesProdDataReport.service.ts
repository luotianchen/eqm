import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class PressVesProdDataReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getpredatasheetreport" , formData, { responseType: 'arraybuffer' });
  }
}

