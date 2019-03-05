import {Component, OnInit} from '@angular/core';
import {DesignAuditService} from './designAudit.service';
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';

@Component({
  selector: 'app-designAudit',
  templateUrl: 'designAudit.component.html',
  styleUrls: ['./designAudit.component.less'],
  providers: [DesignAuditService]
})
export class DesignAuditComponent implements OnInit {
  public dataSet:any;
  public dataSet2:any;
  public dataSet2Display:any;
  placement = 'left';
  public modelData = {
    data:{},
    channel:[],
    saferel:[]
  };
  types = [ { text: 'I', value: 'I'}, { text: 'II', value: 'II' }, { text: 'III', value: 'III' } ];
  loading = true;
  constructor(public designAuditService:DesignAuditService,public message : NzMessageService,public _storage:SessionStorageService){
  }
  ngOnInit(): void {
    this.searchData();
    this.loading = false;
  }
  searchData(){
    this.designAuditService.getaudit().subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
      }
    })
    this.designAuditService.getaudited().subscribe((res)=>{
      if(res['result']=="success"){
        let dwgnos = res['data'];
        this.dataSet2 = [];
        let index = 0;
        for(let item of dwgnos){
          let data = {dwgno:item};
          this.dataSet2.push(data);
          let that = this;
          this.designAuditService.getbydwgno(item).subscribe((res)=>{
            if(res['result'] == "success"){
              for(let i in res['data']){
                that.dataSet2[index][i] = res['data'][i];
              }
              index++;
            }
          });
        }
        this.dataSet2Display = this.dataSet2;
      }
    })
  }
  Audit(dwgno,result){
    this.designAuditService.audit(dwgno,result,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }
  visible = false;

  open(dwgno): void {
    this.designAuditService.getbydwgno(dwgno).subscribe((res)=>{
      this.modelData.data = res["data"];
    });
    this.visible = true;
    this.designAuditService.getsaferel(dwgno).subscribe((res)=>{
      this.modelData.saferel = res["data"];
    });
    this.designAuditService.getchannel(dwgno).subscribe((res)=>{
      this.modelData.channel = res["data"];
    });
  }

  close(): void {
    this.visible = false;
  }
  searchtype = [];
  searchname = '';
  filter(searchtype: string[]): void {
    this.searchtype = searchtype;
    this.search();
  }

  search(): void {
    /** filter data **/
    const filterFunc = item => {
      return (this.searchtype.length ? this.searchtype.some (type => item.type.indexOf(type) !== -1) : true) &&
      (item.deconame.indexOf(this.searchname) !== -1);
    }
    const data = this.dataSet2.filter(item => filterFunc(item));
    /** sort data **/
    this.dataSet2Display = data;
  }
  reset(): void {
    this.searchname = '';
    this.search();
  }
}
