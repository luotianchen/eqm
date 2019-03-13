import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";
import {filter} from "rxjs/internal/operators";

@Injectable()
export class QuaPressVesPlanReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getReport(formData){
    const req = new HttpRequest('POST', this.api.BASEURL+"/getquaplanport" , formData, {
      // reportProgress: true
    });
    return this.http
      .request(req)
      .pipe(filter(e => e instanceof HttpResponse));
  }
}

