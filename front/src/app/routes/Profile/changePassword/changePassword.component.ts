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
    setTimeout(() => this.validateForm.controls['newpasswordconfirm'].updateValueAndValidity());
  }

  confirmValidator = (control: FormControl): { [ s: string ]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.value.newpassword) {
      return { confirm: true, error: true };
    }
  };

  constructor(private fb: FormBuilder,private changePasswordService: ChangePasswordService, private _storage: SessionStorageService, private msg: NzMessageService, private router: Router) {
    this.validateForm = this.fb.group({
      password: [ null, [ Validators.required ] ],
      newpassword: [ null, [ Validators.required ] ],
      newpasswordconfirm : [ null, [ this.confirmValidator ] ]
    });
  }
}
