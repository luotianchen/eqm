import {ModuleWithProviders} from '@angular/core';

import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {CanAuthProvide} from '../core/services/auth.service';
import {Exception404Component} from './exception/exception404.component';
import {WarehousingRegistrationComponent} from "./Material/warehousingRegistration/warehousingRegistration.component";
import {WarehousingQueryComponent} from "./Material/warehousingQuery/warehousingQuery.component";
import {WarehousingAuditComponent} from "./Material/warehousingAudit/warehousingAudit.component";
import {WarrantyAbsentComponent} from "./Material/warrantyAbsent/warrantyAbsent.component";
import {SingleWarrantyAbsentComponent} from "./Material/singleWarrantyAbsent/singleWarrantyAbsent.component";
import {MaterialUsingComponent} from "./Material/materialUsing/materialUsing.component";
import {MonthMaterialComponent} from "./Material/monthMaterial/monthMaterial.component";
import {PressurePartsComponent} from "./Material/pressureParts/pressureParts.component";
import {MaterialDistributeComponent} from "./Material/materialDistribute/materialDistribute.component";
import {WeldingDistributeComponent} from "./Material/weldingDistribute/weldingDistribute.component";
import {PressurePartsAuditComponent} from "./Material/pressurePartsAudit/pressurePartsAudit.component";
import {PressurePartsDistributeComponent} from "./Material/pressurePartsDistribute/pressurePartsDistribute.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent, canLoad: [CanAuthProvide]},
  {
    path: '', component: HomeComponent, canActivate: [CanAuthProvide],
    children: [
      {
        path: 'dashboard', component: DashboardComponent, canActivate: [CanAuthProvide],
      },
      {
        path: 'material/warehousingRegistration', component: WarehousingRegistrationComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料入库登记'
        }
      },
      {
        path: 'material/warehousingQuery', component: WarehousingQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料入库查询'
        }
      },
      {
        path: 'material/warehousingAudit', component: WarehousingAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '入库登记审核'
        }
      },
      {
        path: 'material/warrantyAbsent', component: WarrantyAbsentComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '质保书未到情况查询'
        }
      },
      {
        path: 'material/singleWarrantyAbsent', component: SingleWarrantyAbsentComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '单项质保书未到查询'
        }
      },
      {
        path: 'material/materialUsing', component: MaterialUsingComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料使用情况查询'
        }
      },
      {
        path: 'material/monthMaterial', component: MonthMaterialComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '月材料查询'
        }
      },
      {
        path: 'material/presureParts', component: PressurePartsComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件对照表'
        }
      },{
        path: 'material/materialDistribute', component: MaterialDistributeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料发放登记'
        }
      },{
        path: 'material/weldingDistribute', component: WeldingDistributeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '焊材发放登记'
        }
      },{
        path: 'material/pressurePartsDistribute', component: PressurePartsDistributeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件发放登记'
        }
      },{
        path: 'material/pressurePartsAudit', component: PressurePartsAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件登记审核'
        }
      },
      {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
      {path: '404', component: Exception404Component}
    ]
  },
  {path: '**', redirectTo: '/404', pathMatch: 'full'}
];

