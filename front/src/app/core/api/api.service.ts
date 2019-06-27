/**
 * 统一修改全局接口地址
 */
import {Injectable} from "@angular/core";

@Injectable()
export class ApiService{
   public BASEURL = "http://localhost:8080/eqm_war";
  //public BASEURL = "http://120.55.9.69";
  // public BASEURL = "/eqm";
}
