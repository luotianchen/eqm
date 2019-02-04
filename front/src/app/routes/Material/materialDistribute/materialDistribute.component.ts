import {Component, OnInit, TemplateRef} from '@angular/core';
import {MaterialDistributeService} from "./materialDistribute.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialDistribute',
  templateUrl: 'materialDistribute.component.html',
  styleUrls: ['./materialDistribute.component.less'],
  providers: [MaterialDistributeService]
})
export class MaterialDistributeComponent implements OnInit {
  public prodno:any;
  validateForm: FormGroup;
  partsnameValidateForm: FormGroup;
  status = false;
  i=1;
  partsnames = [];
  designations = [];
  codedmarkings = [];
  users = [];
  username2name = {};
  ngOnInit(): void {
    this.materialDistributeService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodno = res['data'];
      }
    });
    this.materialDistributeService.getPartsname().subscribe(res=>{
      if(res['result']=='success'){
        this.partsnames = res['matlname'];
      }
    });
    this.materialDistributeService.getcodedmarking().subscribe(res=>{
      if(res['result']=="success"){
        this.codedmarkings = res['data'];
      }
    });
    this.materialDistributeService.getputmaterial().subscribe(res=>{
      if(res['result']=='success'){
        this.designations = res['data']['designation'];
      }
    });
    this.materialDistributeService.getuserform().subscribe(res=>{
      if(res['result']=='success'){
        this.users = res['data'];
        for(let user of this.users){
          this.username2name[user.username] = user.name;
        }
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null],
      "dwgno":[null],
    });
    this.partsnameValidateForm = this.fb.group({
      partsname:[null, [Validators.required]],
      enpartsname:[null, [Validators.required]]
    });
    this.updateEditCache();
  }

  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.materialDistributeService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
          this.dataSet = res['data'];
          this.status = true;
          for(;this.i < this.dataSet.length;this.i++){
            this.dataSet[this.i]['key'] = `${this.i}`;
          }
          this.updateEditCache();
        }
      })
    }else{
      this.status = false;
    }
  }
  constructor(public materialDistributeService: MaterialDistributeService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  addRow(): void {
    this.i++;
    this.dataSet = [ ...this.dataSet, {
      "key"    : `${this.i}`,
      "spartname":"",//零件名称
      "spec":"",//规格
      "dimension":"",//尺寸
      "partno":"",//件号
      "designation":"",//牌号
      "qty":"",//数量
      "codedmarking":"",//入库编号
      "issuedate":"",//发料日期
      "picker":"",//领料人
      "ispresspart":"否",//是否为主要受压元件
      "weldno":"",//焊缝号
      "returnqty":"",//退回数量
    } ];
    this.updateEditCache();
    this.editCache[ `${this.i}` ].edit = true;
  }

  deleteRow(i: string): void {
    const dataSet = this.dataSet.filter(d => d.key !== i);
    this.dataSet = dataSet;
  }

  editCache = {};
  dataSet = [];

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

  putdustribute(){
    for(let item in this.editCache){
      if(this.editCache[item].edit){
        this.message.error("您有尚未保存的数据，请保存后再提交！");
        return;
      }
    }

    for(let j = 0;j<this.dataSet.length;j++){
      this.dataSet[j]['issuematl'] = this._storage.get('username');
    }
    let data = [...this.dataSet];
    for(let item of data){
      item.parts
    }
    this.materialDistributeService.putdistribute({
      prodno:this.validateForm.controls['prodno'].value,
      data:this.dataSet
    }).subscribe((res)=>{
      if(res['result']=="success"){
        let modal = this.modalService.success({
          nzTitle: '提交成功',
          nzContent: '发放记录提交成功！'
        });
      }else{
        this.message.error("提交失败，请稍后再试！")
      }
    })
  }

  formatInDate(key) { //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if (monthDay.test(this.editCache[key].data.issuedate)) {
      this.editCache[key].data.issuedate = (new Date().getFullYear() + "-" + this.editCache[key].data.issuedate);
    } else if (!yearMonthDay.test(this.editCache[key].data.issuedate)) {
      this.editCache[key].data.issuedate = null;
    }
  }

  public tplModal: NzModalRef;
  public tplModalButtonLoading = false;
  createTplModal(tplTitle: TemplateRef<{}>, tplContent: TemplateRef<{}>, tplFooter: TemplateRef<{}>): void {
    this.tplModal = this.modalService.create({
      nzTitle: tplTitle,
      nzContent: tplContent,
      nzFooter: tplFooter,
      nzMaskClosable: false,
      nzClosable: true,
      nzOnOk: null
    });
  }

  destroyTplModal(): void {
    this.tplModalButtonLoading = true;
    window.setTimeout(() => {
      this.tplModalButtonLoading = false;
      this.tplModal.destroy();
    }, 1000);
  }

  submitInfo(which){
    if(which == "partsname"){
      for (const i in this.partsnameValidateForm.controls) {
        this.partsnameValidateForm.controls[ i ].markAsDirty();
        this.partsnameValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.partsnameValidateForm.valid){
        this.materialDistributeService.addPartsname(this.partsnameValidateForm.controls['partsname'].value,this.partsnameValidateForm.controls['enpartsname'].value).subscribe(res=>{
          if(res['result'] == "success"){
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.partsnames.push(this.partsnameValidateForm.controls['partsname'].value);
            this.destroyTplModal();
            this.partsnameValidateForm.reset();
            this.partsnameValidateForm.clearValidators();
          }else{
            this.message.error("添加失败，请稍后重试！");
          }
        });
      }
    }
  }
}
