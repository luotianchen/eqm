import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class MaterialReinspectionCommissionQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchrematerialitem',{//获取已审核通过的复验申请
      codedmarking:null,
      status:1
    })
  }
  searchrematerial(){ //获取已审核通过的复验登记，用复验申请减掉已登记的
    return this.http.post(this.api.BASEURL+'/searchrematerial',{
      status:1
    })
  }
}
