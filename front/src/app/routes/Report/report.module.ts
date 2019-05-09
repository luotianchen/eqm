import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {CanAuthProvide} from "../../core/services/auth.service";
import {PressVesShapeSizeInsReportComponent} from "./pressVesShapeSizeInsReport/pressVesShapeSizeInsReport.component";
import {PressCoverReportComponent} from "./pressCoverReport/pressCoverReport.component";
import {QuaPressVesPlanReportComponent} from "./quaPressVesPlanReport/quaPressVesPlanReport.component";
import {VacuumInspectionReportComponent} from "./vacuumInspectionReport/vacuumInspectionReport.component";
import {VacuumTestReportComponent} from "./vacuumTestReport/vacuumTestReport.component";
import {ProdWeldingReportComponent} from "./prodWeldingReport/ProdWeldingReport.component";
import {WelderMatlTraceRecordComponent} from "./welderMatlTraceRecord/welderMatlTraceRecord.component";
import {PressVesProdDataReportComponent} from "./pressVesProdDataReport/PressVesProdDataReport.component";
import {PressVesProdQuaCerReportComponent} from "./pressVesProdQuaCerReport/pressVesProdQuaCerReport.component";
import {LiquidPressTestReportComponent} from "./liquidPressTestReport/liquidPressTestReport.component";
import {PneumaticTestProcedureReportComponent} from "./pneumaticTestProcedureReport/pneumaticTestProcedureReport.component";
import {PressTestReportComponent} from "./pressTestReport/pressTestReport.component";
import {ProdManuChangeReportComponent} from "./prodManuChangeReport/prodManuChangeReport.component";
import {LeaktestReportComponent} from "./leaktestReport/leaktestReport.component";
import {HydraulicTestReportComponent} from "./hydraulicTestReport/hydraulicTestReport.component";
import {ConQuaInfoFbSheetComponent} from "./conQuaInfoFbSheet/conQuaInfoFbSheet.component";
import {CleaningInsReportComponent} from "./cleaningInsReport/cleaningInsReport.component";
import {BarometricReportComponent} from "./barometricReport/barometricReport.component";
import {MeModule} from "../Profile/me/me.module";
import {HydraulicTestReportModule} from "./hydraulicTestReport/hydraulicTestReport.module";
import {BarometricReportModule} from "./barometricReport/barometricReport.module";
import {LeaktestReportModule} from "./leaktestReport/leaktestReport.module";
import {pressTestReportModule} from "./pressTestReport/pressTestReport.module";
import {PneumaticTestProcedureReportModule} from "./pneumaticTestProcedureReport/pneumaticTestProcedureReport.module";
import {LiquidPressTestReportModule} from "./liquidPressTestReport/liquidPressTestReport.module";
import {PressVesProdQuaCerReportModule} from "./pressVesProdQuaCerReport/pressVesProdQuaCerReport.module";
import {VacuumTestReportModule} from "./vacuumTestReport/vacuumTestReport.module";
import {VacuumInspectionReportModule} from "./vacuumInspectionReport/vacuumInspectionReport.module";
import {QuaPressVesPlanReportModule} from "./quaPressVesPlanReport/quaPressVesPlanReport.module";
import {PressCoverReportModule} from "./pressCoverReport/pressCoverReport.module";
import {WelderMatlTraceRecordModule} from "./welderMatlTraceRecord/welderMatlTraceRecord.module";
import {PressVesShapeSizeInsReportModule} from "./pressVesShapeSizeInsReport/pressVesShapeSizeInsReport.module";
import {CleaningInsReportModule} from "./cleaningInsReport/cleaningInsReport.module";
import {ConQuaInfoFbSheetModule} from "./conQuaInfoFbSheet/conQuaInfoFbSheet.module";
import {PressVesProdDataReportReportModule} from "./pressVesProdDataReport/PressVesProdDataReport.module";
import {ProdWeldingReportModule} from "./prodWeldingReport/ProdWeldingReport.module";
import {ProductMaterialReportModule} from "../Material/productMaterialReport/productMaterialReport.module";
import {ProdManuChangeReportModule} from "./prodManuChangeReport/prodManuChangeReport.module";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/report/pressTestReport', pathMatch: 'full'},
      {
        path:'barometricReport', component: BarometricReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '气压试验报告'
        }
      },{
        path:'cleaningInsReport', component: CleaningInsReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '清洁度检查报告'
        }
      },{
        path:'conQuaInfoFbSheet', component: ConQuaInfoFbSheetComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '用户质量信息反馈单'
        }
      },{
        path:'hydraulicTestReport', component: HydraulicTestReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '液压试验报告'
        }
      },{
        path:'leaktestReport', component: LeaktestReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '泄漏性试验检验报告'
        }
      },{
        path:'prodManuChangeReport', component: ProdManuChangeReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品制造变更报告'
        }
      },{
        path: 'pressTestReport', component: PressTestReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '压力试验通知单'
        }
      },{
        path: 'pneumaticTestProcedureReport', component: PneumaticTestProcedureReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '气压（气液组合）试验过程卡'
        }
      },{
        path: 'liquidPressTestReport', component: LiquidPressTestReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '液（水）压试验过程卡'
        }
      },{
        path: 'pressVesProdQuaCerReport', component: PressVesProdQuaCerReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '压力容器产品合格证'
        }
      },{
        path: 'pressVesProdDataReport', component: PressVesProdDataReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '压力容器产品数据表'
        }
      },{
        path:'welderMatlTraceRecord', component: WelderMatlTraceRecordComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '焊工/材料跟踪记录'
        }
      },{
        path:'prodWeldingReport', component: ProdWeldingReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品焊接记录报表'
        }
      },{
        path: 'vacuumTestReport', component: VacuumTestReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '真空考核报告'
        }
      },{
        path: 'vacuumInspectionReport', component: VacuumInspectionReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '真空检测报告'
        }
      },{
        path: 'quaPressVesPlanReport', component: QuaPressVesPlanReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '压力容器产品质量计划'
        }
      },{
        path:'coverReport', component: PressCoverReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '压力容器封面'
        }
      },{
        path:'pressVesShapeSizeInsReport', component: PressVesShapeSizeInsReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '压力容器外观及几何尺寸检验报告'
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
    HydraulicTestReportModule,
    BarometricReportModule,
    LeaktestReportModule,
    pressTestReportModule,
    PneumaticTestProcedureReportModule,
    LiquidPressTestReportModule,
    PressVesProdQuaCerReportModule,
    VacuumTestReportModule,
    VacuumInspectionReportModule,
    QuaPressVesPlanReportModule,
    PressCoverReportModule,
    WelderMatlTraceRecordModule,
    PressVesShapeSizeInsReportModule,
    CleaningInsReportModule,
    ConQuaInfoFbSheetModule,
    PressVesProdDataReportReportModule,
    ProdWeldingReportModule,
    ProdManuChangeReportModule
  ]
})
export class ReportModule {}
