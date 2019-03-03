import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class WaterQualityDetectionInputService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getDepartments(){ //获取所有部门
    return new Promise((resolve, reject) => {
      this.http.get(this.api.BASEURL+"/getdepartment").subscribe(
        result=>{
          resolve(result);
        }
      )
    });
  }
  getprostand(){ //获取产品标准
    return this.http.get(`${this.api.BASEURL}/getprostand`);
  }

  putWaterQuality(data){
    return this.http.post(`${this.api.BASEURL}/putwatertest`,data);
  }
}
