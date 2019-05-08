import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import {CanAuthProvide} from "../../core/services/auth.service";
import {MaterialDistributionLedgerComponent} from "./materialDistributionLedger/materialDistributionLedger.component";
import {InspectionRecordsComponent} from "./inspectionRecords/inspectionRecords.component";
import {PressurePartsReportComponent} from "./pressurePartsReport/pressurePartsReport.component";
import {MaterialSubstitutionQueryComponent} from "./materialSubstitutionQuery/materialSubstitutionQuery.component";
import {MaterialSubstitutionAuditComponent} from "./materialSubstitutionAudit/materialSubstitutionAudit.component";
import {MaterialSubstitutionComponent} from "./materialSubstitution/materialSubstitution.component";
import {WarehouseEntryNoticeComponent} from "./warehouseEntryNotice/warehouseEntryNotice.component";
import {ProductMaterialReportComponent} from "./productMaterialReport/productMaterialReport.component";
import {PressurePartsAuditComponent} from "./pressurePartsAudit/pressurePartsAudit.component";
import {PressurePartsDistributeComponent} from "./pressurePartsDistribute/pressurePartsDistribute.component";
import {WeldingDistributeComponent} from "./weldingDistribute/weldingDistribute.component";
import {MaterialDistributeComponent} from "./materialDistribute/materialDistribute.component";
import {PressurePartsComponent} from "./pressureParts/pressureParts.component";
import {CertificateReportComponent} from "./certificateReport/certificateReport.component";
import {MonthMaterialComponent} from "./monthMaterial/monthMaterial.component";
import {MaterialUsingComponent} from "./materialUsing/materialUsing.component";
import {SingleWarrantyAbsentComponent} from "./singleWarrantyAbsent/singleWarrantyAbsent.component";
import {WarrantyAbsentComponent} from "./warrantyAbsent/warrantyAbsent.component";
import {WarehousingAuditComponent} from "./warehousingAudit/warehousingAudit.component";
import {WarehousingQueryComponent} from "./warehousingQuery/warehousingQuery.component";
import {WarehousingRegistrationComponent} from "./warehousingRegistration/warehousingRegistration.component";
import {WarehousingRegistrationModule} from "./warehousingRegistration/warehousingRegistration.module";
import {WarehousingQueryModule} from "./warehousingQuery/warehousingQuery.module";
import {WarehousingAuditModule} from "./warehousingAudit/warehousingAudit.module";
import {WarrantyAbsentModule} from "./warrantyAbsent/warrantyAbsent.module";
import {SingleWarrantyAbsentModule} from "./singleWarrantyAbsent/singleWarrantyAbsent.module";
import {MaterialUsingModule} from "./materialUsing/materialUsing.module";
import {MonthMaterialModule} from "./monthMaterial/monthMaterial.module";
import {PressurePartsModule} from "./pressureParts/pressureParts.module";
import {MaterialDistributeModule} from "./materialDistribute/materialDistribute.module";
import {WeldingDistributeModule} from "./weldingDistribute/weldingDistribute.module";
import {PressurePartsAuditModule} from "./pressurePartsAudit/pressurePartsAudit.module";
import {PressurePartsDistributeModule} from "./pressurePartsDistribute/pressurePartsDistribute.module";
import {WarehouseEntryNoticeModule} from "./warehouseEntryNotice/warehouseEntryNotice.module";
import {MaterialSubstitutionModule} from "./materialSubstitution/materialSubstitution.module";
import {MaterialSubstitutionAuditModule} from "./materialSubstitutionAudit/materialSubstitutionAudit.module";
import {MaterialSubstitutionQueryModule} from "./materialSubstitutionQuery/materialSubstitutionQuery.module";
import {PressurePartsReportModule} from "./pressurePartsReport/pressurePartsReport.module";
import {MaterialDistributionLedgerModule} from "./materialDistributionLedger/materialDistributionLedger.module";
import {InspectionRecordsModule} from "./inspectionRecords/inspectionRecords.module";
import {CertificateReportModule} from "./certificateReport/certificateReport.module";
import {ProductMaterialReportModule} from "./productMaterialReport/productMaterialReport.module";

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/material/warehousingRegistration', pathMatch: 'full'},
      {
        path: 'warehousingRegistration', component: WarehousingRegistrationComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料入库登记'
        }
      },
      {
        path: 'warehousingQuery', component: WarehousingQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料入库查询'
        }
      },
      {
        path: 'warehousingAudit', component: WarehousingAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '入库登记审核'
        }
      },
      {
        path: 'warrantyAbsent', component: WarrantyAbsentComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '质保书未到情况查询'
        }
      },
      {
        path: 'singleWarrantyAbsent', component: SingleWarrantyAbsentComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '单项质保书未到查询'
        }
      },
      {
        path: 'materialUsing', component: MaterialUsingComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料使用情况查询'
        }
      },
      {
        path: 'monthMaterial', component: MonthMaterialComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '月材料查询'
        }
      },
      {
        path: 'approvalCertificate', component: CertificateReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '检验合格证'
        }
      },
      {
        path: 'presureParts', component: PressurePartsComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件对照表'
        }
      },{
        path: 'materialDistribute', component: MaterialDistributeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料发放登记'
        }
      },{
        path: 'weldingDistribute', component: WeldingDistributeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '焊材发放登记'
        }
      },{
        path: 'pressurePartsDistribute', component: PressurePartsDistributeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件发放登记'
        }
      },{
        path: 'pressurePartsAudit', component: PressurePartsAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件登记审核'
        }
      },{
        path:'productMaterialReport', component: ProductMaterialReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品材料清单报表'
        }
      },{
        path: 'warehouseEntryNotice', component: WarehouseEntryNoticeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '入库通知单生成'
        }
      },{
        path: 'materialSubstitution', component: MaterialSubstitutionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料代用申请'
        }
      },{
        path: 'materialSubstitutionAudit', component: MaterialSubstitutionAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料代用审核'
        }
      },{
        path: 'materialSubstitutionQuery', component: MaterialSubstitutionQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '代用通知单查询'
        }
      },{
        path: 'pressurePartsReport', component: PressurePartsReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件使用材料一览表'
        }
      },{
        path: 'distributionLedger', component: MaterialDistributionLedgerComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料发放台帐'
        }
      },{
        path: 'inspectionRecords', component: InspectionRecordsComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '检验记录报表'
        }
      },]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    WarehousingRegistrationModule,
    WarehousingQueryModule,
    WarehousingAuditModule,
    WarrantyAbsentModule,
    SingleWarrantyAbsentModule,
    MaterialUsingModule,
    MonthMaterialModule,
    PressurePartsModule,
    MaterialDistributeModule,
    WeldingDistributeModule,
    PressurePartsAuditModule,
    PressurePartsDistributeModule,
    WarehouseEntryNoticeModule,
    MaterialSubstitutionModule,
    MaterialSubstitutionAuditModule,
    MaterialSubstitutionQueryModule,
    PressurePartsReportModule,
    MaterialDistributionLedgerModule,
    InspectionRecordsModule,
    CertificateReportModule,
    ProductMaterialReportModule,
  ]
})
export class MaterialModule {}
