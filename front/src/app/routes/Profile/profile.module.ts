import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {ChangePasswordComponent} from "./changePassword/changePassword.component";
import {ChangePasswordModule} from "./changePassword/changePassword.module";
import {MeModule} from "./me/me.module";
import {MeComponent} from "./me/me.component";
import {CanAuthProvide} from "../../core/services/auth.service";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/profile/me', pathMatch: 'full'},
      {
        path: 'me', component: MeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '个人资料'
        }
      },{
        path: 'changePassword', component: ChangePasswordComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '修改密码'
        }
      }
    ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    MeModule,
    ChangePasswordModule
  ]
})
export class ProfileModule {}
