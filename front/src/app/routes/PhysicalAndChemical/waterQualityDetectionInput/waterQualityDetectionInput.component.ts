import {Component, OnInit, TemplateRef} from '@angular/core';
import {WaterQualityDetectionInputService} from "./waterQualityDetectionInput.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-waterQualityDetectionInput',
  templateUrl: 'waterQualityDetectionInput.component.html',
  styleUrls: ['./waterQualityDetectionInput.component.less'],
  providers: [WaterQualityDetectionInputService]
})
export class  WaterQualityDetectionInputComponent implements OnInit {
  public codedmarkings:any;
  validateForm: FormGroup;
  units = [];
  prostands = [];
  latestData = {
    unit:"",
    indate: "",
    qty: "",
    name: "",
    testno: "",
    roomno: "",
    testcont: "",
    testrst: "",
    stand: "",
    testdate: "",
    date: "",
    user: ""
  };
  ngOnInit(): void {
    this.waterQualityDetectionInputService.getDepartments().then((res)=>{
      if(res['result'] == "success"){
        console.log(res)
        this.units = res['data'].filter(item=>item.department!=0);
      }
    });
    this.waterQualityDetectionInputService.getprostand().subscribe(res=>{
      if(res['result'] == "success"){
        this.prostands = res['data'];
      }
    });
    this.waterQualityDetectionInputService.getWaterQuality().subscribe((res:any)=>{
      if(res['result'] == "success"){
        this.latestData = res;
      }
    })
    this.validateForm = this.fb.group({
      unit:[null,[Validators.required]],//委托单位
      indate:[null,[Validators.required]],//接收样品日期
      qty:[null,[Validators.required]],//数量
      name:['自来水',[Validators.required]],//样品名称
      testno:[null,[Validators.required]],//试样编号
      roomno:['/',[Validators.required]],//本室编号
      testcont:['氯离子含量',[Validators.required]],//测试内容
      testrst:[null,[Validators.required,Validators.max(25)]],//测试结果
      stand:[null,[Validators.required]],//评定标准
      testdate:[null,[Validators.required]],//检测日期
      user:[this._storage.get('username'),[Validators.required]],//提交人
    });
  }

  formatInDate(control){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }

  constructor(public waterQualityDetectionInputService:WaterQualityDetectionInputService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.waterQualityDetectionInputService.putWaterQuality(this.validateForm.value).subscribe((res:any)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
          this.validateForm.reset();
        }
      })
    }
  }
}
