import {Component} from '@angular/core';
import {ChangePasswordService} from "./changePassword.service";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {NzMessageService} from "ng-zorro-antd";
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Observable, Observer} from "rxjs/index";
import {Router} from "@angular/router";
@Component({
  selector: 'app-changePassword',
  templateUrl: 'changePassword.component.html',
  styleUrls: ['./changePassword.component.less'],
  providers: [ChangePasswordService]
})
export class ChangePasswordComponent {
  ngOnInit(): void {
  }
  passwordStrength = "简单";
  passwordStatus = "exception";
  passwordPecent = 0;
  passwordVisible = false;
  newPasswordVisible = false;
  check(){
    let level_weak =/^((\d+)|([A-Za-z]+)|(\W+)){6,16}$/;
    let level_middle = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![,\.#%'\+\*\-:;^_`]+$)[,\.#%'\+\*\-:;^_`0-9A-Za-z]{6,16}$/;
    let level_strong = /^(?:(?=.*[0-9].*)(?=.*[A-Za-z].*)(?=.*[,\.#%'\+\*\-:;^_`].*))[,\.#%'\+\*\-:;^_`0-9A-Za-z]{6,16}$/;
    if(this.validateForm.value.newpassword.length>16){
      this.passwordStrength = '长度最多为16位';
      this.passwordStatus = "exception";
      this.passwordPecent = 0;
    }else if(this.validateForm.value.newpassword.length<6) {
      this.passwordStrength = '长度至少为6位';
      this.passwordStatus = "exception";
      this.passwordPecent = 0;
    }else if(level_strong.test(this.validateForm.value.newpassword)){
      this.passwordStrength = "复杂";
      this.passwordStatus = "success";
      this.passwordPecent = 80+this.validateForm.value.newpassword.length>100?100:80+this.validateForm.value.newpassword.length;
    }else if(level_middle.test(this.validateForm.value.newpassword)){
      this.passwordStrength = "中等";
      this.passwordStatus = "active";
      this.passwordPecent = 40+this.validateForm.value.newpassword.length>70?70:40+this.validateForm.value.newpassword.length;
    }else if(level_weak.test(this.validateForm.value.newpassword)){
      this.passwordStrength = "简单";
      this.passwordStatus = "exception";
      this.passwordPecent = 10+this.validateForm.value.newpassword.length>30?30:10+this.validateForm.value.newpassword.length;
    }
  }

  validateForm: FormGroup;
  submitForm = ($event, value) => {
    $event.preventDefault();
    for (const key in this.validateForm.controls) {
      this.validateForm.controls[ key ].markAsDirty();
      this.validateForm.controls[ key ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.changePasswordService.changePassword(this._storage.get("username"),this.validateForm.value.password,this.validateForm.value.newpassword).subscribe((res)=>{
        if(res['result']=="success"){
          this.msg.success("修改成功！");
          this._storage.clear();
          this.router.navigate(['login']);
        }else{
          this.msg.error("旧密码错误！");
        }
      })
    }
  };

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.validateForm.reset();
    for (const key in this.validateForm.controls) {
      this.validateForm.controls[ key ].markAsPristine();
      this.validateForm.controls[ key ].updateValueAndValidity();
    }
  }

  validateConfirmPassword(): void {
    this.check();
    setTimeout(() => this.validateForm.controls['newpasswordconfirm'].updateValueAndValidity());
  }

  confirmValidator = (control: FormControl): { [ s: string ]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.value.newpassword) {
      return { confirm: true, error: true };
    }
  };

  constructor(public fb: FormBuilder,public changePasswordService: ChangePasswordService, public _storage: SessionStorageService, public msg: NzMessageService, public router: Router) {
    this.validateForm = this.fb.group({
      password: [ null, [ Validators.required ] ],
      newpassword: [ null, [ Validators.required,Validators.pattern(/^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![,\.#%'\+\*\-:;^_`]+$)[,\.#%'\+\*\-:;^_`0-9A-Za-z]{6,16}$/)]],
        newpasswordconfirm : [ null, [ this.confirmValidator ] ]
    });
  }
}
