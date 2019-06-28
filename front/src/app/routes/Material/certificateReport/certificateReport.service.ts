import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class CertificateReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getcodedmarking(){
    return this.http.post(this.api.BASEURL+"/getcodedmarking",{});
  }
  getReport(codedmarking){
    return this.http.post(this.api.BASEURL+"/getinspectionreport",{codedmarking:codedmarking});
  }
  getlogo(){
    return this.http.get(`${this.api.BASEURL}/getlogo`);
  }
}

