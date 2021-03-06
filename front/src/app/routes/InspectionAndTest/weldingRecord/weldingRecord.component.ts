import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {WeldingRecordService} from "./weldingRecord.service";

@Component({
  selector: 'app-weldingRecord',
  templateUrl: 'weldingRecord.component.html',
  styleUrls: ['./weldingRecord.component.less'],
  providers: [WeldingRecordService]
})
export class WeldingRecordComponent implements OnInit {
  public prodnos:any;
  validateForm: FormGroup;
  dataSet = [];
  users = [];
  users2 = [];
  status = false;
  i = 1;
  ngOnInit(): void {
    this.weldingRecordService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.weldingRecordService.getUserNames().subscribe(res=>{
      if(res['result'] == "success"){
        this.users = [];
        this.users2 = [];
        for(let user of res['data']){
          if(this.checkRole(user)){
            this.users.push(user);
          }
          if(this.checkRole2(user)){
            this.users2.push(user);
          }
        }
      }
    })
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
    });
  }

  checkRole(user:any){
    return (user.role==55 || user.role2==55 ||  user.role3==55 ||  user.role4==55 ||  user.role5==55 );
  }

  checkRole2(user:any){
    return (user.role==51 ||user.role2==51 ||user.role3==51 ||user.role4==51 ||user.role5==51);
  }

  searchData(): void {
    this.editCache = {};
    this.dataSet = [];
    this.updateEditCache();
    if(this.validateForm.value.prodno!=null && this.validateForm.value.prodno!=""){
      this.weldingRecordService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
          this.status = true;
        }else{
          this.status = false;
        }
      })
      this.weldingRecordService.getWeldingRecord(this.validateForm.value.prodno).subscribe((res:any)=>{
        if(res['result'] == "success"){
          if(res['data'].length>0){
            this.dataSet = res['data'];
            for(let data of this.dataSet){
              data.key = this.i++;
              if(!!data.usernote)
                data.usernote = data.usernote.split('/').filter(item=>item);
            }
            this.updateEditCache();
          }
        }
      })
    }
  }
  constructor(public weldingRecordService: WeldingRecordService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  formatInDate(key,datetype){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.editCache[key].data[datetype])){
      this.editCache[key].data[datetype] = new Date().getFullYear()+"-"+this.editCache[key].data[datetype];
    }else if(!yearMonthDay.test(this.editCache[key].data[datetype])){
      this.editCache[key].data[datetype] = null;
    }
    this.check(key);
  }
  check(key){
    if(this.editCache[key].data.ndtdate && this.editCache[key].data.entrustdate){
      let ndtdate = this.editCache[key].data.ndtdate.split('-'),entrustdate = this.editCache[key].data.entrustdate.split('-');
      console.log(ndtdate);
      if(parseInt(ndtdate[0]) < parseInt(entrustdate[0])){
        this.editCache[key].data['ndtdate'] = null;
      }else if(parseInt(ndtdate[1]) < parseInt(entrustdate[1])){
        this.editCache[key].data['ndtdate'] = null;
      }else if(parseInt(ndtdate[2]) < parseInt(entrustdate[2])){
        this.editCache[key].data['ndtdate'] = null;
      }
    }
    if(this.editCache[key].data.welddate && this.editCache[key].data.entrustdate){
      let welddate = this.editCache[key].data.welddate.split('-'),entrustdate = this.editCache[key].data.entrustdate.split('-');
      if(parseInt(entrustdate[0]) < parseInt(welddate[0])){
        this.editCache[key].data['entrustdate'] = null;
      }else if(parseInt(entrustdate[1]) < parseInt(welddate[1])){
        this.editCache[key].data['entrustdate'] = null;
      }else if(parseInt(entrustdate[2]) < parseInt(welddate[2])){
        this.editCache[key].data['entrustdate'] = null;
      }
    }
  }
  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    let dataset = this.dataSet;
    for(let data of dataset){
      if(data['welddate'] == null){
        this.message.error("施焊日期不能为空！");
        return;
      }
      data.usernote = data.usernote.join('/')
    }
    if(this.validateForm.valid){
      this.weldingRecordService.putWeldingRecord({
        user:this._storage.get('username'),
        prodno:this.validateForm.value.prodno,
        prodname:this.validateForm.value.prodname,
        dwgno:this.validateForm.value.dwgno,
        data:dataset
      }).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '提交成功',
            nzContent: '焊接记录提交成功！'
          });
          this.searchData();
        }
      })
    }
  }

  addRow(): void {
    this.i++;
    this.dataSet = [ ...this.dataSet, {
      "key"    : `${this.i}`,
      "weldno":null,
      "weldevano":null,
      "weldmethod":null,
      "usernote":[],
      "welddate":null,
      "inspector":null,
      "entrustdate":null,
      "ndtdate":null
    } ];
    this.updateEditCache();
    this.editCache[ `${this.i}` ].edit = true;
  }

  deleteRow(i: string): void {
    const dataSet = this.dataSet.filter(d => d.key !== i);
    this.dataSet = dataSet;
    this.updateEditCache();
  }
  copyRow(i: string): void {
    this.i++;
    let dataSet = this.dataSet.filter(item=>item.key ==i)[0];
    this.dataSet = [ ...this.dataSet, {
      "key"    : `${this.i}`,
      "weldno":dataSet.weldno,
      "weldevano":dataSet.weldevano,
      "weldmethod":dataSet.weldmethod,
      "usernote":!!dataSet.usernote?dataSet.usernote.slice():null,
      "welddate":dataSet.welddate,
      "inspector":dataSet.inspector,
      "entrustdate":dataSet.entrustdate,
      "ndtdate":dataSet.ndtdate,
    } ];
    this.updateEditCache();
    this.editCache[ `${this.i}` ].edit = true;
  }
  editCache = {};

  startEdit(key: string): void {
    this.editCache[ key ].edit = true;
    this.updateEditCache();
  }

  cancelEdit(key: string): void {
    this.editCache[ key ].edit = false;
    this.updateEditCache();
  }

  saveEdit(key: string): void {
    const index = this.dataSet.findIndex(item => item.key === key);
    Object.assign(this.dataSet[ index ], this.editCache[ key ].data);
    // this.dataSet[ index ] = this.editCache[ key ].data;
    this.editCache[ key ].edit = false;
    this.updateEditCache();
  }

  updateEditCache(): void {
    this.dataSet.forEach(item => {
      if (!this.editCache[ item.key ]) {
        this.editCache[ item.key ] = {
          edit: false,
          data: { ...item }
        };
      }
    });
  }

}
