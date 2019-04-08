import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class ProductTestBoardCommissionReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getAudit(prodno){
    return this.http.post(this.api.BASEURL+'/searchproductplatedata',{
      status:1,
      prodno:prodno
    })
  }
  getDepartments(){
    return this.http.get(this.api.BASEURL+"/getdepartment");
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getphychereport" , formData, { responseType: 'arraybuffer' });
  }
}
