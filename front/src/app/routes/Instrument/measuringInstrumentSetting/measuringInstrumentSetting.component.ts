import {Component, OnInit} from '@angular/core';
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
  constructor(public fb: FormBuilder, public measuringInstrumentSettingService: MeasuringInstrumentSettingService,public _storage: SessionStorageService,public msg:NzMessageService,public modalService: NzModalService) {
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      system_email:[null,[Validators.required,Validators.email]],
      authorization_code:[null,[Validators.required]],
      tosend_email:[null,[Validators.required]],
    })
    this.measuringInstrumentSettingService.getUsers().subscribe((res)=>{
      if(res['result']=="success"){
        this.userform = res['data'];
      }
    })
    this.measuringInstrumentSettingService.getMeasSetting().subscribe((res)=>{
      if(res['result']=="success"){
        this.validateForm.controls['system_email'].setValue(res['data']['system_email']);
        this.validateForm.controls['authorization_code'].setValue(res['data']['authorization_code']);
        this.validateForm.controls['tosend_email'].setValue(res['data']['tosend_email'].filter(item=>item));
      }
    })
  }

  check(){
    for(let item of this.validateForm.value.tosend_email)
      if(!item)
        this.validateForm.controls['tosend_email'].setErrors({required:true})
      else
        this.validateForm.controls['tosend_email'].setErrors(null)
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
  info(): void {
    this.modalService.confirm({
      nzTitle: '发送测试邮件确认',
      nzContent: '<p>本次测试将会通过发件人给所有收件人发送一封测试邮件，请勿使用本功能频繁发送测试邮件，否则可能会被对方识别为垃圾邮件！</p>',
      nzOkText    : '我确认',
      nzOkType    : 'danger',
      nzOnOk      : () => this.infoLogin(),
      nzCancelText: '取消发送',
      nzOnCancel  : () => console.log('取消发送')
    });
  }
  isVisible = false;
  isConfirmLoading = false;
  btntext = '确认发送';
  infoLogin(): void {
    this.isVisible = true;
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  loginValidateForm:FormGroup = null;
  onAuthSubmit(form) {
    this.loginValidateForm = form;
    for (const i in this.loginValidateForm.controls) {
      this.loginValidateForm.controls[ i ].markAsDirty();
      this.loginValidateForm.controls[ i ].updateValueAndValidity();
    }
  }
  handleOk(): void {
    this.isConfirmLoading = true;
    this.btntext = '发送中，请勿关闭窗口……'
    this.measuringInstrumentSettingService.testEmail(this.loginValidateForm.value.userName,this.loginValidateForm.value.password).subscribe(res=>{
      this.btntext = '确认发送';
      if(res['result'] == "success"){
        this.msg.success("发送成功！请接受者检查邮箱")
        this.isVisible = false;
        this.isConfirmLoading = false;
      }else{
        this.msg.error("登录失败，请检查用户名和密码！")
        this.isConfirmLoading = false;
      }
    },err=>{
      this.btntext = '确认发送';
      this.msg.error("发送失败！请稍后重试")
      this.isConfirmLoading = false;
    })
  }
}
