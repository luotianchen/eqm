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
  public dataSet2 = [];
  validateForm:FormGroup;
  public dataSet2Display = [];
  placement = 'left';
  public modelData = {
    data:{},
    channel:[],
    saferel:[]
  };
  dwgnonow = null;
  types = [ { text: 'I', value: 'I'}, { text: 'II', value: 'II' }, { text: 'III', value: 'III' } ];
  loading = true;
  constructor(public designQueryService:DesignQueryService,public fb:FormBuilder,public message : NzMessageService,public _storage:SessionStorageService){
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      "dwgno":[null],
    });
    this.searchData();
  }
  searchData(){
    this.designQueryService.getaudited().subscribe((res:any)=>{
      if(res['result']=="success"){
        this.loading = false;
        this.dataSet2 = res['data'];
        this.dataSet2Display = this.dataSet2.filter(item=>!this.validateForm.value.dwgno || item.dwgno == this.validateForm.value.dwgno);
      }
    })
  }
  visible = false;

  open2(dwgno): void {
    this.dwgnonow = dwgno;
    this.designQueryService.getbydwgno(dwgno,1).subscribe((res:any)=>{
      this.modelData.data = res["data"];
    });
    this.visible = true;
    this.designQueryService.getsaferel(dwgno,1).subscribe((res:any)=>{
      this.modelData.saferel = res["data"].reverse();
    });
    this.designQueryService.getchannel(dwgno,1).subscribe((res:any)=>{
      this.modelData.channel = res["data"];
    });
  }

  close(): void {
    this.visible = false;
  }
  searchtype = [];
  searchname = '';
  filter2(searchtype: string[]): void {
    this.searchtype = searchtype;
    this.search();
  }

  search(): void {
    let typemap = {};
    for(let i of this.searchtype) typemap[i] = true;
    const data = this.dataSet2.filter((item)=>(item.deconame.indexOf(this.searchname) !== -1) && (this.searchtype.length ? typemap[item.type]:true));
    /** sort data **/
    this.dataSet2Display = data.filter(item=>!this.validateForm.value.dwgno || item.dwgno == this.validateForm.value.dwgno);
  }
  reset(): void {
    this.searchname = '';
    this.search();
  }
  trimNumber(str){
    return str.replace(/\d+/g,'');
  }
}
