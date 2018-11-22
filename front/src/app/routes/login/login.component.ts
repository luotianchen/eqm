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

  submitForm() {
    for (const element of Object(this.validateForm.controls)) {
      element.markAsDirty();
    }
    if (this.validateForm.valid) {
      this.loadStatus = true;
      this.loginBtn = '登录中...';
      const username = this.validateForm.value.userName;
      const password = this.validateForm.value.password;
      this.loginService.login(username, password).subscribe(res => {
        if (res['result'] === 'success') {
          this._storage.set('username', username);
          this._storage.set('name', name);
          this.router.navigate(['']);
        } else {
          this.loadStatus = false;
          this.loginBtn = '登录';
          this.message.error('账号或密码错误！');
        }
      });
    }
  }

  constructor(private fb: FormBuilder, private message: NzMessageService,
              private loginService: LoginService, private router: Router, private _storage: SessionStorageService) {
  }

  getFormControl(name) {
    return this.validateForm.controls[name];
  }

  ngOnInit() {
    if(this._storage.get('username')!=null){
      this.router.navigate(['']);
    }
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [ true ]
    });
  }
}
