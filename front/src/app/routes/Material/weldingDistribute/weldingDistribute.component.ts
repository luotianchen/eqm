import {Component, OnInit, TemplateRef} from '@angular/core';
import {WeldingDistributeService} from "./weldingDistribute.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialDistribute',
  templateUrl: 'weldingDistribute.component.html',
  styleUrls: ['./weldingDistribute.component.less'],
  providers: [WeldingDistributeService]
})
export class WeldingDistributeComponent implements OnInit {
  private prodno:any;
  validateForm: FormGroup;
  matlnameValidateForm: FormGroup;
  status = false;
  i=1;
  ngOnInit(): void {
    this.weldingDistributeService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodno = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null],
      "dwgno":[null],
    });
    this.matlnameValidateForm = this.fb.group({
      matlname:[null, [Validators.required]]
    });
    this.updateEditCache();
  }

  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.weldingDistributeService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
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
  constructor(private weldingDistributeService: WeldingDistributeService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
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
      "ispresspart":"",//是否为主要受压元件
      "weldno":"",//焊缝号
      "returnqty":"",//退回数量
      "note":""//备注
    }];
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
      this.dataSet[j]['issuematl'] = this._storage.get('name');
    }
    this.weldingDistributeService.putdistribute({
      prodno:this.validateForm.controls['prodno'].value[0],
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

  formatInDate(key){ //日期格式化
    let monthDay = /^([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.editCache[key].data.issuedate)){
      this.editCache[key].data.issuedate = (new Date().getFullYear()+"-"+this.editCache[key].data.issuedate);
    }else if(!yearMonthDay.test(this.editCache[key].data.issuedate)){
      this.editCache[key].data.issuedate = null;
    }
  }



  private tplModal: NzModalRef;
  private tplModalButtonLoading = false;
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
    if(which == "matlname"){
      for (const i in this.matlnameValidateForm.controls) {
        this.matlnameValidateForm.controls[ i ].markAsDirty();
        this.matlnameValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.matlnameValidateForm.valid){
        this.weldingDistributeService.addMatlname(this.matlnameValidateForm.controls['matlname'].value).subscribe(res=>{
          if(res['result'] == "success"){
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.destroyTplModal();
          }else{
            this.message.error("添加失败，请稍后重试！");
            this.destroyTplModal();
          }
        });
      }
    }
  }
}
