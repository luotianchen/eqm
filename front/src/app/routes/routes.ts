import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {CanAuthProvide} from '../core/services/auth.service';
import {Exception404Component} from './exception/exception404.component';
import {Exception403Component} from "./exception/exception403.component";

export const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: '', component: HomeComponent, canActivate: [CanAuthProvide],
    children: [
      { path: '', redirectTo: '/dashboard', pathMatch: 'full'},
      { path: 'dashboard', component: DashboardComponent, canActivate: [CanAuthProvide]}, //首页
      { path: 'material',loadChildren: './Material/material.module#MaterialModule'}, //材料模块
      { path: 'physicalAndChemical',loadChildren: './PhysicalAndChemical/physicalAndChemical.module#PhysicalAndChemicalModule'}, //理化模块
      { path: 'inspectionAndTest',loadChildren: './InspectionAndTest/inspectionAndTest.module#InspectionAndTestModule'}, //检验与试验数据录入模块
      { path: 'instrument',loadChildren: './Instrument/instrument.module#InstrumentModule' },//计量台账模块
      { path: 'design',loadChildren: './Design/design.module#DesignModule'}, //设计模块
      { path: 'report',loadChildren: './Report/report.module#ReportModule' },//检验与试验报表模块
      { path: 'profile',loadChildren: './Profile/profile.module#ProfileModule'}, //个人信息模块
      { path: 'system',loadChildren: './System/system.module#SystemModule'}, //系统信息设置模块
      //异常页面
      { path: '404', component: Exception404Component},
      { path: '500', component: Exception404Component},
      { path: '403', component: Exception403Component},
    ]
  },
  { path: '**', redirectTo: '/404', pathMatch: 'full'}
];
