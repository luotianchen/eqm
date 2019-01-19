import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {MaterialSubstitutionService} from "./materialSubstitution.service";

@Component({
  selector: 'app-materialSubstitution',
  templateUrl: 'materialSubstitution.component.html',
  styleUrls: ['./materialSubstitution.component.less'],
  providers: [MaterialSubstitutionService]
})
export class MaterialSubstitutionComponent implements OnInit {
  validateForm: FormGroup;
  prodno = null;
  prodnos = [];
  why = "";
  ngOnInit(): void {
    this.materialSubstitutionService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null],
      "dwgno":[null],
    });
  }

  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.materialSubstitutionService.getdistribute(this.validateForm.value.prodno).subscribe((res) => {
        if(res['result']=="success") {
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
          this.prodno = this.validateForm.value.prodno;
        }else{
          this.prodno = null;
        }
      })
    }
  }
  constructor(private materialSubstitutionService: MaterialSubstitutionService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }

  i = 1;
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
  addRow(): void {
    this.i++;
    this.dataSet = [ ...this.dataSet, {
      "key"    : `${this.i}`,
      "name":"",//零件名称
      "designmatl":"",//设计材料材质
      "designspec":"",//设计材料规格
      "substitutematl":"",//代用材料材质
      "substitutespec":"",//代用材料规格
      "type":"",//代用材料类别
    } ];
    this.updateEditCache();
    this.editCache[ `${this.i}` ].edit = true;
  }

  deleteRow(i: string): void {
    const dataSet = this.dataSet.filter(d => d.key !== i);
    this.dataSet = dataSet;
  }

  putSubstitution(){
    for(let item in this.editCache){
      if(this.editCache[item].edit){
        this.message.error("您有尚未保存的数据，请保存后再提交！");
        return;
      }
    }
    if(this.dataSet.length>5){
      this.message.error("每次提交的代用数据不能超过5条！");
      return;
    }
    const data = {
      prodno:null,
      user:null,
      why:null,
      data:null
    };
    data.prodno = this.prodno;
    data.user = this._storage.get("name");
    data.why = this.why;
    data.data = this.dataSet;
    this.materialSubstitutionService.putSubstitution(data).subscribe((res)=>{
      if(res['result']=="success"){
        let modal = this.modalService.success({
          nzTitle: '提交成功',
          nzContent: '代用申请提交成功！'
        });
        this.validateForm = this.validateForm = this.fb.group({
          "prodno":[null, [Validators.required]],
          "prodname":[null],
          "dwgno":[null],
        });
        this.prodno = null;
        this.dataSet = null;
        this.why = null;
      }else{
        this.message.error("提交失败，请稍后再试！")
      }
    })
  }

}
