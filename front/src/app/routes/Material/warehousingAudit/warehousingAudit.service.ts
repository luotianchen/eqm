import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class WarehousingAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getputmaterial() {
    return this.http.get(this.api.BASEURL+'/getputmaterial');
  }
  searchHeatbatchno(heatbatchno){
    return this.http.post(this.api.BASEURL+'/searchheatbatchno',{status:0,heatbatchno:heatbatchno});
  }
  getCodedmarking(){
    return this.http.post(this.api.BASEURL+"/searchallmaterial",{
      pageindex:1,
      pagesize:99999,
      searchdata:{
        codedmarking:null,
        matlname:null,
        designation:null,
        spec:null,
        millunit:null,
        indate:null,
        status:0
      }
    })
  }
  searchallmaterial(pageindex,pagesize,codedmarking,matlname,designation,spec,millunit,heatbatchno,indate){
    return this.http.post(this.api.BASEURL+"/searchallmaterial",{
      pageindex:pageindex,
      pagesize:pagesize,
      searchdata:{
        codedmarking:codedmarking,
        matlname:matlname,
        designation:designation,
        spec:spec,
        millunit:millunit,
        heatbatchno:heatbatchno,
        indate:indate,
        status:0
      }
    })
  }
  audit(codedmarking,status,audit_user){
    return this.http.post(this.api.BASEURL+"/putaudit",{codedmarking:codedmarking,status:status,audit_user:audit_user})
  }

  putmessage(receive_user,codedmarking,audit_user){
    return this.http.post(this.api.BASEURL+"/putmessage",{title:"材料入库审核未通过",content:"您的入库编号为"+codedmarking+"的入库信息被"+audit_user+"审核拒绝",send_type:"text",send_user:"系统提示",recieve_user:receive_user})
  }
  getuserform() {
    return this.http.get(`${this.api.BASEURL}/getuserform`);
  }
}
