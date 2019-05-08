import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {CanAuthProvide} from "../../core/services/auth.service";
import {WeldingRecordModule} from "./weldingRecord/weldingRecord.module";
import {ProductManufacturingParametersQueryModule} from "./productManufacturingParametersQuery/productManufacturingParametersQuery.module";
import {ProductManufacturingParametersModule} from "./productManufacturingParameters/productManufacturingParameters.module";
import {ProductManufacturingParametersAuditModule} from "./productManufacturingParametersAudit/productManufacturingParametersAudit.module";
import {VacuumParameterAuditModule} from "./vacuumParameterAudit/vacuumParameterAudit.module";
import {VacuumParameterModule} from "./vacuumParameter/vacuumParameter.module";
import {TestParametersAuditModule} from "./testParametersAudit/testParametersAudit.module";
import {TestParametersModule} from "./testParameters/testParameters.module";
import {TestParametersComponent} from "./testParameters/testParameters.component";
import {TestParametersAuditComponent} from "./testParametersAudit/testParametersAudit.component";
import {VacuumParameterComponent} from "./vacuumParameter/vacuumParameter.component";
import {VacuumParameterAuditComponent} from "./vacuumParameterAudit/vacuumParameterAudit.component";
import {WeldingRecordComponent} from "./weldingRecord/weldingRecord.component";
import {ProductManufacturingParametersComponent} from "./productManufacturingParameters/productManufacturingParameters.component";
import {ProductManufacturingParametersAuditComponent} from "./productManufacturingParametersAudit/productManufacturingParametersAudit.component";
import {ProductManufacturingParametersQueryComponent} from "./productManufacturingParametersQuery/productManufacturingParametersQuery.component";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/inspectionAndTest/testParameters', pathMatch: 'full'},
      {
        path: 'testParameters', component: TestParametersComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '试压参数输入'
        }
      }
      ,{
        path: 'testParametersAudit', component: TestParametersAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '试压参数审核'
        }
      }
      ,{
        path: 'vacuumParameters', component: VacuumParameterComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '真空参数输入'
        }
      },{
        path: 'vacuumParameterAudit', component: VacuumParameterAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '真空参数审核'
        }
      },{
        path: 'productManufacturingParameters', component: ProductManufacturingParametersComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品制造参数输入'
        }
      },{
        path: 'productManufacturingParametersAudit', component: ProductManufacturingParametersAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品制造参数审核'
        }
      },{
        path: 'productManufacturingParametersQuery', component: ProductManufacturingParametersQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品制造参数查询'
        }
      },{
        path: 'weldingRecord', component: WeldingRecordComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '焊接记录输入'
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
    TestParametersModule,
    TestParametersAuditModule,
    VacuumParameterModule,
    VacuumParameterAuditModule,
    ProductManufacturingParametersAuditModule,
    ProductManufacturingParametersModule,
    ProductManufacturingParametersQueryModule,
    WeldingRecordModule
  ]
})
export class InspectionAndTestModule {}
