import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class MechanicalReportForProTestBoardService {
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
  searchbyprodno(prodno){
    return this.http.post(this.api.BASEURL+"/searchbyprodno",{prodno:prodno});
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getmepretestreport" , formData, { responseType: 'arraybuffer' });
  }
}
