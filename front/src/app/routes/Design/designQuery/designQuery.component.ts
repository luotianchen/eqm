import {Component, OnInit} from '@angular/core';
import {DesignQueryService} from './designQuery.service';
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-designQuery',
  templateUrl: 'designQuery.component.html',
  styleUrls: ['./designQuery.component.less'],
  providers: [DesignQueryService]
})
export class DesignQueryComponent implements OnInit {
  public dataSet2:any;
  validateForm:FormGroup;
  public dataSet2Display:any;
  placement = 'left';
  public modelData = {
    data:{},
    channel:[],
    saferel:[]
  };
  types = [ { text: 'I', value: 'I'}, { text: 'II', value: 'II' }, { text: 'III', value: 'III' } ];
  loading = true;
  constructor(public designQueryService:DesignQueryService,public fb:FormBuilder,public message : NzMessageService,public _storage:SessionStorageService){
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      "dwgno":[null],
    })
    this.searchData();
  }
  searchData(){
    this.designQueryService.getaudited().subscribe((res)=>{
      if(res['result']=="success"){
        this.loading = false;
        let dwgnos = res['data'];
        this.dataSet2 = [];
        let index = 0;
        for(let item of dwgnos){
          let data = {dwgno:item};
          this.dataSet2.push(data);
          let that = this;
          this.designQueryService.getbydwgno(item,1).subscribe((res)=>{
            if(res['result'] == "success"){
              for(let i in res['data']){
                that.dataSet2[index][i] = res['data'][i];
              }
              index++;
            }
          });
        }
        this.dataSet2Display = this.dataSet2.reverse().filter(item=>!this.validateForm.value.dwgno || item.dwgno == this.validateForm.value.dwgno);
      }
    })
  }
  visible = false;

  open2(dwgno): void {
    this.designQueryService.getbydwgno(dwgno,1).subscribe((res)=>{
      this.modelData.data = res["data"];
    });
    this.visible = true;
    this.designQueryService.getsaferel(dwgno,1).subscribe((res)=>{
      this.modelData.saferel = res["data"].reverse();
    });
    this.designQueryService.getchannel(dwgno,1).subscribe((res)=>{
      this.modelData.channel = res["data"].reverse();
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
    this.dataSet2Display = data.reverse();
  }
  reset(): void {
    this.searchname = '';
    this.search();
  }
}
