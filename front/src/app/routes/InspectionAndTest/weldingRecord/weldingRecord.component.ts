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
  names = [];
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
        this.names = res['data'];
      }
    })
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
      "user":[this._storage.get("username")]
    });
  }

  searchData(): void {
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
      if(ndtdate[0] < entrustdate[0]){
        this.editCache[key].data['ndtdate'] = null;
      }else if(ndtdate[1] < entrustdate[1]){
        this.editCache[key].data['ndtdate'] = null;
      }else if(ndtdate[2] < entrustdate[2]){
        this.editCache[key].data['ndtdate'] = null;
      }
    }
    if(this.editCache[key].data.welddate && this.editCache[key].data.entrustdate){
      let welddate = this.editCache[key].data.welddate.split('-'),entrustdate = this.editCache[key].data.entrustdate.split('-');
      if(welddate[0] > entrustdate[0]){
        this.editCache[key].data['entrustdate'] = null;
      }else if(welddate[1] > entrustdate[1]){
        this.editCache[key].data['entrustdate'] = null;
      }else if(welddate[2] > entrustdate[2]){
        this.editCache[key].data['entrustdate'] = null;
      }
    }
  }
  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.weldingRecordService.putWeldingRecord(this.validateForm.value).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '提交成功',
            nzContent: '焊接记录提交成功！'
          });
          this.validateForm.reset();
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
      "usernote":null,
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
  }

  editCache = {};

  startEdit(key: string): void {
    this.editCache[ key ].edit = true;
  }

  cancelEdit(key: string): void {
    this.editCache[ key ].edit = false;
  }

  saveEdit(key: string): void {
    const index = this.dataSet.findIndex(item => item.key === key);
    Object.assign(this.dataSet[ index ], this.editCache[ key ].data);
    // this.dataSet[ index ] = this.editCache[ key ].data;
    this.editCache[ key ].edit = false;
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
