import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {UserComponent} from "./user/user.component";
import {RoleComponent} from "./role/role.component";
import {CanAuthProvide} from "../../core/services/auth.service";
import {AccessPermissionComponent} from "./accessPermission/accessPermission.component";
import {SettingComponent} from "./setting/setting.component";
import {AccessPermissionModule} from "./accessPermission/accessPermission.module";
import {RoleModule} from "./role/role.module";
import {UserModule} from "./user/user.module";
import {SettingModule} from "./setting/setting.module";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/system/setting', pathMatch: 'full'},
      {
        path: 'setting', component: SettingComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '系统设置'
        }
      },{
        path: 'user', component: UserComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '用户管理'
        }
      },{
        path: 'role', component: RoleComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '角色管理'
        }
      },{
        path: 'accessPermission', component: AccessPermissionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '访问权限设置'
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
    SettingModule,
    UserModule,
    RoleModule,
    AccessPermissionModule
  ]
})
export class SystemModule {}
