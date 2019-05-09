import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {CanAuthProvide} from "../../core/services/auth.service";
import {ProductTestBoardDataRegistrationComponent} from "./productTestBoardDataRegistration/productTestBoardDataRegistration.component";
import {ProductTestBoardCommissionComponent} from "./productTestBoardCommission/productTestBoardCommission.component";
import {ProductTestBoardCommissionAuditComponent} from "./productTestBoardCommissionAudit/productTestBoardCommissionAudit.component";
import {ProductTestBoardCommissionQueryComponent} from "./productTestBoardCommissionQuery/productTestBoardCommissionQuery.component";
import {ProductTestBoardDataRegistrationAuditComponent} from "./productTestBoardDataRegistrationAudit/productTestBoardDataRegistrationAudit.component";
import {ProductTestBoardDataRegistrationQueryComponent} from "./productTestBoardDataRegistrationQuery/productTestBoardDataRegistrationQuery.component";
import {ProductTestBoardCommissionReportComponent} from "./productTestBoardCommissionReport/productTestBoardCommissionReport.component";
import {MechanicalReportForProTestBoardComponent} from "./mechanicalReportForProTestBoard/mechanicalReportForProTestBoard.component";
import {MaterialReinspectionCommissionComponent} from "./materialReinspectionCommission/materialReinspectionCommission.component";
import {MaterialReinspectionCommissionAuditComponent} from "./materialReinspectionCommissionAudit/materialReinspectionCommissionAudit.component";
import {MaterialReinspectionCommissionQueryComponent} from "./materialReinspectionCommissionQuery/materialReinspectionCommissionQuery.component";
import {MaterialReinspectionCommissionReportComponent} from "./materialReinspectionCommissionReport/materialReinspectionCommissionReport.component";
import {MechanicalReportForMatlReinspectionComponent} from "./mechanicalReportForMatlReinspection/mechanicalReportForMatlReinspection.component";
import {ChemicalAnalysisTestReportComponent} from "./chemicalAnalysisTestReport/chemicalAnalysisTestReport.component";
import {MaterialReinspectionComponent} from "./materialReinspection/materialReinspection.component";
import {MaterialReinspectionAuditComponent} from "./materialReinspectionAudit/materialReinspectionAudit.component";
import {MaterialReinspectionQueryComponent} from "./materialReinspectionQuery/materialReinspectionQuery.component";
import {WaterQualityDetectionInputComponent} from "./waterQualityDetectionInput/waterQualityDetectionInput.component";
import {ProductTestBoardDataRegistrationModule} from "./productTestBoardDataRegistration/productTestBoardDataRegistration.module";
import {ProductTestBoardCommissionModule} from "./productTestBoardCommission/productTestBoardCommission.module";
import {ProductTestBoardCommissionAuditModule} from "./productTestBoardCommissionAudit/productTestBoardCommissionAudit.module";
import {ProductTestBoardCommissionQueryModule} from "./productTestBoardCommissionQuery/productTestBoardCommissionQuery.module";
import {MaterialReinspectionCommissionModule} from "./materialReinspectionCommission/materialReinspectionCommission.module";
import {MaterialReinspectionCommissionAuditModule} from "./materialReinspectionCommissionAudit/materialReinspectionCommissionAudit.module";
import {MaterialReinspectionCommissionQueryModule} from "./materialReinspectionCommissionQuery/materialReinspectionCommissionQuery.module";
import {MaterialReinspectionModule} from "./materialReinspection/materialReinspection.module";
import {MaterialReinspectionAuditModule} from "./materialReinspectionAudit/materialReinspectionAudit.module";
import {MaterialReinspectionQueryModule} from "./materialReinspectionQuery/materialReinspectionQuery.module";
import {ProductTestBoardDataRegistrationAuditModule} from "./productTestBoardDataRegistrationAudit/productTestBoardDataRegistrationAudit.module";
import {ProductTestBoardDataRegistrationQueryModule} from "./productTestBoardDataRegistrationQuery/productTestBoardDataRegistrationQuery.module";
import {MaterialReinspectionCommissionReportModule} from "./materialReinspectionCommissionReport/materialReinspectionCommissionReport.module";
import {ProductTestBoardCommissionReportModule} from "./productTestBoardCommissionReport/productTestBoardCommissionReport.module";
import {ChemicalAnalysisTestReportModule} from "./chemicalAnalysisTestReport/chemicalAnalysisTestReport.module";
import {MechanicalReportForProTestBoardModule} from "./mechanicalReportForProTestBoard/mechanicalReportForProTestBoard.module";
import {MechanicalReportForMatlReinspectionModule} from "./mechanicalReportForMatlReinspection/mechanicalReportForMatlReinspection.module";
import {WaterQualityDetectionInputModule} from "./waterQualityDetectionInput/waterQualityDetectionInput.module";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/physicalAndChemical/materialReinspectionCommission', pathMatch: 'full'},
      {
        path: 'productTestBoardDataRegistration', component: ProductTestBoardDataRegistrationComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板参数输入'
        }
      },{
        path: 'productTestBoardCommission', component: ProductTestBoardCommissionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板委托'
        }
      },{
        path: 'productTestBoardCommissionAudit', component: ProductTestBoardCommissionAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板审核'
        }
      },{
        path: 'productTestBoardCommissionQuery', component: ProductTestBoardCommissionQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板查询'
        }
      },{
        path: 'productTestBoardDataRegistrationAudit', component: ProductTestBoardDataRegistrationAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板数据审核'
        }
      },{
        path: 'productTestBoardDataRegistrationQuery', component: ProductTestBoardDataRegistrationQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板数据查询'
        }
      },{
        path: 'ProductTestBoardCommissionReport', component: ProductTestBoardCommissionReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '理化试验委托单报表'
        }
      },{
        path: 'mechanicalReportForProTestBoard', component: MechanicalReportForProTestBoardComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '力学性能试验报告'
        }
      },{
        path: 'materialReinspectionCommission', component: MaterialReinspectionCommissionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料复验申请'
        }
      },{
        path: 'materialReinspectionCommissionAudit',component:MaterialReinspectionCommissionAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料复验申请审核'
        }
      },{
        path: 'materialReinspectionCommissionQuery', component:MaterialReinspectionCommissionQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料复验申请查询'
        }
      },{
        path: 'materialReinspectionCommissionReport', component:MaterialReinspectionCommissionReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料复验申请单报表'
        }
      },{
        path: 'mechanicalReportForMatlReinspection', component: MechanicalReportForMatlReinspectionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '力学性能试验报告'
        }
      },{
        path: 'chemicalAnalysisTestReport', component:ChemicalAnalysisTestReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '化学分析报告'
        }
      },{
        path: 'materialReinspection', component: MaterialReinspectionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料复验登记'
        }
      },{
        path: 'materialReinspectionAudit', component: MaterialReinspectionAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料复验登记审核'
        }
      },{
        path: 'materialReinspectionQuery', component: MaterialReinspectionQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料复验登记查询'
        }
      },{
        path: 'waterQualityDetectionInput', component: WaterQualityDetectionInputComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '水质检验输入'
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
    ProductTestBoardDataRegistrationModule,
    ProductTestBoardCommissionModule,
    ProductTestBoardCommissionAuditModule,
    ProductTestBoardCommissionQueryModule,
    MaterialReinspectionCommissionModule,
    MaterialReinspectionCommissionAuditModule,
    MaterialReinspectionCommissionQueryModule,
    MaterialReinspectionModule,
    MaterialReinspectionAuditModule,
    MaterialReinspectionQueryModule,
    ProductTestBoardDataRegistrationAuditModule,
    ProductTestBoardDataRegistrationQueryModule,
    MaterialReinspectionCommissionReportModule,
    ProductTestBoardCommissionReportModule,
    ChemicalAnalysisTestReportModule,
    MechanicalReportForProTestBoardModule,
    MechanicalReportForMatlReinspectionModule,
    WaterQualityDetectionInputModule
  ]
})
export class PhysicalAndChemicalModule {}
