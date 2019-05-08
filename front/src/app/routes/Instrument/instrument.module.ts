import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {CanAuthProvide} from "../../core/services/auth.service";
import {MeasuringInstrumentLedgerComponent} from "./measuringInstrumentLedger/measuringInstrumentLedger.component";
import {MeasuringInstrumentLedgerAuditComponent} from "./measuringInstrumentLedgerAudit/measuringInstrumentLedgerAudit.component";
import {MeasuringInstrumentLedgerQueryComponent} from "./measuringInstrumentLedgerQuery/measuringInstrumentLedgerQuery.component";
import {MeasuringInstrumentSettingComponent} from "./measuringInstrumentSetting/measuringInstrumentSetting.component";
import {MeasuringInstrumentLedgerModule} from "./measuringInstrumentLedger/measuringInstrumentLedger.module";
import {MeasuringInstrumentLedgerAuditModule} from "./measuringInstrumentLedgerAudit/measuringInstrumentLedgerAudit.module";
import {MeasuringInstrumentLedgerQueryModule} from "./measuringInstrumentLedgerQuery/measuringInstrumentLedgerQuery.module";
import {MeasuringInstrumentSettingModule} from "./measuringInstrumentSetting/measuringInstrumentSetting.module";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/instrument/measuringInstrumentLedger', pathMatch: 'full'},
      {
        path: 'measuringInstrumentLedger', component: MeasuringInstrumentLedgerComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '计量器具台帐输入'
        }
      },{
        path: 'measuringInstrumentLedgerAudit', component: MeasuringInstrumentLedgerAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '计量器具台帐审核'
        }
      },{
        path: 'measuringInstrumentLedgerQuery', component: MeasuringInstrumentLedgerQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '计量器具台帐查询'
        }
      },{
        path: 'measuringInstrumentSetting', component: MeasuringInstrumentSettingComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '设备计量台帐邮箱设置'
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
    MeasuringInstrumentLedgerModule,
    MeasuringInstrumentLedgerAuditModule,
    MeasuringInstrumentLedgerQueryModule,
    MeasuringInstrumentSettingModule,
  ]
})
export class InstrumentModule {}
