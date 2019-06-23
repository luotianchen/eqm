import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class CertificateReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getcodedmarking(codedmarking){
    return this.http.post(this.api.BASEURL+"/getcodedmarkingcache",{codedmarking:codedmarking});
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getinspectionreport" , formData, { responseType: 'arraybuffer' });
  }
  getlogo(){
    return this.http.get(`${this.api.BASEURL}/getlogo`);
  }
}

