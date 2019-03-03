import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class ProductTestBoardCommissionQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchprotestboardcom',{
      prodno:null,
      status:1
    })
  }
  searchproductplatedata(){ //获取已审核通过的数据登记，与上面取剪辑
    return this.http.post(this.api.BASEURL+'/searchproductplatedata',{status:1})
  }
}
