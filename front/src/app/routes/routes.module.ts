import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DashboardModule} from './dashboard/dashboard.module';
import {HomeModule} from './home/home.module';
import {LoginModule} from './login/login.module';
import {routes} from './routes';
import {ExceptionModule} from './exception/exception.module';
import {WarehousingRegistrationModule} from "./Material/warehousingRegistration/warehousingRegistration.module";
import {WarehousingQueryModule} from "./Material/warehousingQuery/warehousingQuery.module";
import {WarehousingAuditModule} from "./Material/warehousingAudit/warehousingAudit.module";
import {WarrantyAbsentModule} from "./Material/warrantyAbsent/warrantyAbsent.module";
import {SingleWarrantyAbsentModule} from "./Material/singleWarrantyAbsent/singleWarrantyAbsent.module";
import {MaterialUsingModule} from "./Material/materialUsing/materialUsing.module";
import {MonthMaterialModule} from "./Material/monthMaterial/monthMaterial.module";
import {PressurePartsModule} from "./Material/pressureParts/pressureParts.module";
import {MaterialDistributeModule} from "./Material/materialDistribute/materialDistribute.module";
import {WeldingDistributeModule} from "./Material/weldingDistribute/weldingDistribute.module";
import {PressurePartsAuditModule} from "./Material/pressurePartsAudit/pressurePartsAudit.module";
import {PressurePartsDistributeModule} from "./Material/pressurePartsDistribute/pressurePartsDistribute.module";
import {ProfileModule} from "./Profile/profile/profile.module";
import {ChangePasswordModule} from "./Profile/changePassword/changePassword.module";
import {UserModule} from "./System/user/user.module";
import {RoleModule} from "./System/role/role.module";
import {WarehouseEntryNoticeModule} from "./Material/warehouseEntryNotice/warehouseEntryNotice.module";
import {MaterialSubstitutionModule} from "./Material/materialSubstitution/materialSubstitution.module";
import {MaterialSubstitutionAuditModule} from "./Material/materialSubstitutionAudit/materialSubstitutionAudit.module";
import {MaterialSubstitutionQueryModule} from "./Material/materialSubstitutionQuery/materialSubstitutionQuery.module";
import {DesignInputModule} from "./Design/designInput/designInput.module";
import {DesignAuditModule} from './Design/designAudit/designAudit.module';
import {PressurePartsReportModule} from './Material/pressurePartsReport/pressurePartsReport.module';
import {MaterialDistributionLedgerModule} from './Material/materialDistributionLedger/materialDistributionLedger.module';
import {TestParametersModule} from './InspectionAndTest/testParameters/testParameters.module';
import {InspectionRecordsModule} from './Material/inspectionRecords/inspectionRecords.module';
import {TestParametersAuditModule} from './InspectionAndTest/testParametersAudit/testParametersAudit.module';
import {MeasuringInstrumentLedgerModule} from './Instrument/measuringInstrumentLedger/measuringInstrumentLedger.module';
import {MeasuringInstrumentLedgerAuditModule} from './Instrument/measuringInstrumentLedgerAudit/measuringInstrumentLedgerAudit.module';
import {MeasuringInstrumentLedgerQueryModule} from './Instrument/measuringInstrumentLedgerQuery/measuringInstrumentLedgerQuery.module';
import {VacuumParameterModule} from './InspectionAndTest/vacuumParameter/vacuumParameter.module';
import {ProductTestBoardDataRegistrationModule} from './PhysicalAndChemical/productTestBoardDataRegistration/productTestBoardDataRegistration.module';
import {MeasuringInstrumentSettingModule} from './Instrument/measuringInstrumentSetting/measuringInstrumentSetting.module';
import {ProductManufacturingParametersModule} from './InspectionAndTest/productManufacturingParameters/productManufacturingParameters.module';
import {ProductTestBoardCommissionModule} from './PhysicalAndChemical/productTestBoardCommission/productTestBoardCommission.module';
import {VacuumParameterAuditModule} from './InspectionAndTest/vacuumParameterAudit/vacuumParameterAudit.module';
import {ProductTestBoardCommissionAuditModule} from './PhysicalAndChemical/productTestBoardCommissionAudit/productTestBoardCommissionAudit.module';
import {ProductTestBoardCommissionQueryModule} from './PhysicalAndChemical/productTestBoardCommissionQuery/productTestBoardCommissionQuery.module';
import {ProductTestBoardDataRegistrationAuditModule} from './PhysicalAndChemical/productTestBoardDataRegistrationAudit/productTestBoardDataRegistrationAudit.module';
import {ProductTestBoardDataRegistrationQueryModule} from './PhysicalAndChemical/productTestBoardDataRegistrationQuery/productTestBoardDataRegistrationQuery.module';
import {
  MaterialReinspectionCommissionModule
} from './PhysicalAndChemical/materialReinspectionCommission/materialReinspectionCommission.module';
import {MaterialReinspectionCommissionAuditModule} from './PhysicalAndChemical/materialReinspectionCommissionAudit/materialReinspectionCommissionAudit.module';
import {MaterialReinspectionCommissionQueryModule} from './PhysicalAndChemical/materialReinspectionCommissionQuery/materialReinspectionCommissionQuery.module';
import {MaterialReinspectionModule} from './PhysicalAndChemical/materialReinspection/materialReinspection.module';
import {ProductManufacturingParametersAuditModule} from './InspectionAndTest/productManufacturingParametersAudit/productManufacturingParametersAudit.module';
import {MaterialReinspectionAuditModule} from './PhysicalAndChemical/materialReinspectionAudit/materialReinspectionAudit.module';
import {MaterialReinspectionQueryModule} from './PhysicalAndChemical/materialReinspectionQuery/materialReinspectionQuery.module';
import {SettingModule} from './System/setting/setting.module';
import {pressTestReportModule} from './Report/pressTestReport/pressTestReport.module';
import {PneumaticTestProcedureReportModule} from './Report/pneumaticTestProcedureReport/pneumaticTestProcedureReport.module';
import {LiquidPressTestReportModule} from './Report/liquidPressTestReport/liquidPressTestReport.module';
import {PressVesProdQuaCerReportModule} from './Report/pressVesProdQuaCerReport/pressVesProdQuaCerReport.module';
import {VacuumTestReportModule} from './Report/vacuumTestReport/vacuumTestReport.module';
import {VacuumInspectionReportModule} from './Report/vacuumInspectionReport/vacuumInspectionReport.module';
import {QuaPressVesPlanReportModule} from './Report/quaPressVesPlanReport/quaPressVesPlanReport.module';
import {CertificateReportModule} from "./Material/certificateReport/certificateReport.module";
import {PressCoverReportModule} from "./Report/pressCoverReport/pressCoverReport.module";
import {WelderMatlTraceRecordModule} from "./Report/welderMatlTraceRecord/welderMatlTraceRecord.module";
import {PressVesShapeSizeInsReportModule} from "./Report/pressVesShapeSizeInsReport/pressVesShapeSizeInsReport.module";
import {CleaningInsReportModule} from "./Report/cleaningInsReport/cleaningInsReport.module";
import {ConQuaInfoFbSheetModule} from "./Report/conQuaInfoFbSheet/conQuaInfoFbSheet.module";
import {WeldingRecordModule} from "./InspectionAndTest/weldingRecord/weldingRecord.module";
import {WaterQualityDetectionInputModule} from "./PhysicalAndChemical/waterQualityDetectionInput/waterQualityDetectionInput.module";
import {AccessPermissionModule} from "./System/accessPermission/accessPermission.module";
import {PressVesProdDataReportReportModule} from "./Report/pressVesProdDataReport/PressVesProdDataReport.module";
import {ProdWeldingReportModule} from "./Report/prodWeldingReport/ProdWeldingReport.module";
import {ProductMaterialReportModule} from "./Material/productMaterialReport/productMaterialReport.module";
import {DesignQueryModule} from "./Design/designQuery/designQuery.module";
@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true}),
  ],
  exports: [
    DashboardModule,
    HomeModule,
    LoginModule,
    ExceptionModule,
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
    ProfileModule,
    ChangePasswordModule,
    UserModule,
    RoleModule,
    WarehouseEntryNoticeModule,
    MaterialSubstitutionModule,
    MaterialSubstitutionAuditModule,
    MaterialSubstitutionQueryModule,
    DesignInputModule,
    DesignAuditModule,
    DesignQueryModule,
    PressurePartsReportModule,
    MaterialDistributionLedgerModule,
    InspectionRecordsModule,
    TestParametersModule,
    TestParametersAuditModule,
    MeasuringInstrumentLedgerModule,
    MeasuringInstrumentLedgerAuditModule,
    MeasuringInstrumentLedgerQueryModule,
    MeasuringInstrumentSettingModule,
    VacuumParameterModule,
    ProductTestBoardDataRegistrationModule,
    ProductTestBoardCommissionModule,
    ProductManufacturingParametersModule,
    VacuumParameterAuditModule,
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
    ProductManufacturingParametersAuditModule,
    SettingModule,
    pressTestReportModule,
    PneumaticTestProcedureReportModule,
    LiquidPressTestReportModule,
    PressVesProdQuaCerReportModule,
    VacuumTestReportModule,
    VacuumInspectionReportModule,
    QuaPressVesPlanReportModule,
    CertificateReportModule,
    PressCoverReportModule,
    WelderMatlTraceRecordModule,
    PressVesShapeSizeInsReportModule,
    CleaningInsReportModule,
    ConQuaInfoFbSheetModule,
    WeldingRecordModule,
    WaterQualityDetectionInputModule,
    AccessPermissionModule,
    PressVesProdDataReportReportModule,
    ProdWeldingReportModule,
    ProductMaterialReportModule
  ]
})

export class RoutesModule {

}
