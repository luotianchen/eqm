import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class MaterialReinspectionCommissionReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getCodedmarking(){
    return this.http.post(this.api.BASEURL+'/searchrematerialitem',{//获取已审核通过的复验申请
      codedmarking:null,
      status:1
    })
  }
  getReport(formData){
    return this.http.post(this.api.BASEURL+"/getrematerialreport" , formData, { responseType: 'arraybuffer' });
  }
}
