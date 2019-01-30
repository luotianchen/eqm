import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {NzMessageService, NzModalRef, NzModalService} from 'ng-zorro-antd';
import {SettingService} from './setting.service';

@Component({
  selector: 'app-setting',
  templateUrl: 'setting.component.html',
  styleUrls: ['./setting.component.less'],
  providers: [SettingService]
})

export class SettingComponent implements OnInit {
  validateForm: FormGroup;
  constructor(public fb: FormBuilder, public settingService: SettingService,public _storage: SessionStorageService,public msg:NzMessageService,public modalService: NzModalService) {
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      "deconame":[null,[Validators.required]],
      "edeconame":[null,[Validators.required]],
      "delicense":[null,[Validators.required]],
      "manulevel":[null,[Validators.required]],
      "time":[null,[Validators.required]],
      "orgcode":[null,[Validators.required]],
      "name":[null,[Validators.required]],
      "ename":[null,[Validators.required]],
      "uniformcode":[null,[Validators.required]],
      "manuno":[null,[Validators.required]]
    });
    this.settingService.getSettingInfo().subscribe((res)=>{
      if(res['result']=="success"){
        this.validateForm.controls['deconame'].setValue(res['deconame']);
        this.validateForm.controls['edeconame'].setValue(res['edeconame']);
        this.validateForm.controls['delicense'].setValue(res['delicense']);
        this.validateForm.controls['manulevel'].setValue(res['manulevel']);
        this.validateForm.controls['time'].setValue(res['time']);
        this.validateForm.controls['orgcode'].setValue(res['orgcode']);
        this.validateForm.controls['name'].setValue(res['name']);
        this.validateForm.controls['ename'].setValue(res['ename']);
        this.validateForm.controls['uniformcode'].setValue(res['uniformcode']);
        this.validateForm.controls['manuno'].setValue(res['manuno']);
      }
    })
  }

  submitForm(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.settingService.putSettingInfo(this.validateForm.value).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '设置成功',
            nzContent: '设置系统参数成功！'
          })
        }
      })
    }
  }

  formatDate(control){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }
}
