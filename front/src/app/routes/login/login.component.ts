import {Component, OnInit} from '@angular/core';
import {FormGroup, Validators, FormBuilder} from '@angular/forms';
import {LoginService} from './login.service';
import {Router} from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import {SessionStorageService} from '../../core/storage/storage.module';

@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['./login.component.less'],
  providers: [LoginService]
})

export class LoginComponent implements OnInit {
  validateForm: FormGroup;
  loadStatus: boolean;
  loginBtn = '登录';
  passwordVisible = false;
  submitForm() {
    for (const element of Object(this.validateForm.controls)) {
      element.markAsDirty();
    }
    if (this.validateForm.valid) {
      this.loadStatus = true;
      this.loginBtn = '登录中...';
      const username = this.validateForm.value.userName;
      const password = this.validateForm.value.password;

      this.loginService.login(username, password).then((res: any) => {
        this.getRoutePower();
        if (res['result'] === 'success') {
          this._storage.set('username', username);
          if(res['name']!=null)
            this._storage.set('name', res['name']);
          if(res['role']!=null)
            this._storage.set('role', res['role']);
          if(res['role2']!=null)
            this._storage.set('role2', res['role2']);
          if(res['role3']!=null)
            this._storage.set('role3', res['role3']);
          if(res['role4']!=null)
            this._storage.set('role4', res['role4']);
          if(res['role5']!=null)
            this._storage.set('role5', res['role5']);
          if(res['roles']!=null)
            this._storage.set('roles', res['roles']);
          if(res['email']!=null)
            this._storage.set('email', res['email']);
          this.router.navigate(['dashboard']);
        }else if(res['result'] == -2){
          this.loadStatus = false;
          this.loginBtn = '登录';
          this.message.error('系统尚未激活或已使用期限到期！');
        }else if(res['result'] == -3){
          this.loadStatus = false;
          this.loginBtn = '登录';
          this.message.error('激活码与当前服务器机器不匹配！');
        } else {
          this.loadStatus = false;
          this.loginBtn = '登录';
          this.message.error('账号或密码错误！');
        }
      },(err)=>{
        this.loadStatus = false;
        this.loginBtn = '登录';
        this.message.error('登录失败，请检查网络后重试！');
      });
    }
  }

  constructor(public fb: FormBuilder, public message: NzMessageService,
              public loginService: LoginService, public router: Router, public _storage: SessionStorageService) {
  }

  getFormControl(name) {
    return this.validateForm.controls[name];
  }

  getRoutePower(){
    this.loginService.getroutepower().subscribe(res=>{
      if(res['result'] == "success"){
      this._storage.set('powermap',res['data'])
    }
  })
  }

  ngOnInit() {
    this.getRoutePower();
    if(this._storage.get('username')!=null){
      this.router.navigate(['dashboard']);
    }
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [ true ]
    });
  }
  forgetPWD(){
    this.message.error("请联系超级管理员重置密码！");
  }
}
