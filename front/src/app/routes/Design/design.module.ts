import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {CanAuthProvide} from "../../core/services/auth.service";
import {DesignInputComponent} from "./designInput/designInput.component";
import {DesignAuditComponent} from "./designAudit/designAudit.component";
import {DesignQueryComponent} from "./designQuery/designQuery.component";
import {DesignAuditModule} from "./designAudit/designAudit.module";
import {DesignQueryModule} from "./designQuery/designQuery.module";
import {DesignInputModule} from "./designInput/designInput.module";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/design/input', pathMatch: 'full'},
        {
        path: 'input', component: DesignInputComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '设计参数输入'
        }
      },{
        path: 'audit', component: DesignAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '设计参数审核'
        }
      },{
        path: 'query', component: DesignQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '设计参数查询'
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
    DesignInputModule,
    DesignAuditModule,
    DesignQueryModule
  ]
})
export class DesignModule {}
