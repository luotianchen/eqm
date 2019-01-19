import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {NzMessageService, NzModalRef, NzModalService} from 'ng-zorro-antd';
import {MeasuringInstrumentSettingService} from './measuringInstrumentSetting.service';

@Component({
  selector: 'app-measuringInstrumentSetting',
  templateUrl: 'measuringInstrumentSetting.component.html',
  styleUrls: ['./measuringInstrumentSetting.component.less'],
  providers: [MeasuringInstrumentSettingService]
})
export class MeasuringInstrumentSettingComponent implements OnInit {
  validateForm: FormGroup;
  userform = [];
  constructor(private fb: FormBuilder, private measuringInstrumentSettingService: MeasuringInstrumentSettingService,private _storage: SessionStorageService,private msg:NzMessageService,private modalService: NzModalService) {
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      system_email:[null,[Validators.required]],
      authorization_code:[null,[Validators.required]],
      tosend_email:[null,[Validators.required]],
    });
    this.measuringInstrumentSettingService.getUsers().subscribe((res)=>{
      if(res['result']=="success"){
        this.userform = res['data'];
      }
    })
    this.measuringInstrumentSettingService.getMeasSetting().subscribe((res)=>{
      if(res['result']=="success"){
        this.validateForm.controls['system_email'].setValue(res['data']['system_email']);
        this.validateForm.controls['authorization_code'].setValue(res['data']['authorization_code']);
        this.validateForm.controls['tosend_email'].setValue(res['data']['tosend_email']);
      }
    })
  }

  submitForm(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.measuringInstrumentSettingService.putMeasSetting(this.validateForm.value).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '设置成功',
            nzContent: '计量台帐邮箱设置成功！'
          })
        }
      })
    }
  }
}
