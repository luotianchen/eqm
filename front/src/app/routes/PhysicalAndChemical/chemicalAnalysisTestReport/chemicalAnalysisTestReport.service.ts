import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class ChemicalAnalysisTestReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getCodedmarking(){
    return this.http.post(this.api.BASEURL+'/searchrematerial',{
      codedmarking:null,
      status:1
    })
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getcheanareport" , formData, { responseType: 'arraybuffer' });
  }
  getDepartments(){
    return this.http.get(this.api.BASEURL+"/getdepartment");
  }
}
