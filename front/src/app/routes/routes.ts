import {Routes} from '@angular/router';
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
import {ProfileComponent} from "./Profile/profile/profile.component";
import {ChangePasswordComponent} from "./Profile/changePassword/changePassword.component";
import {UserComponent} from "./System/user/user.component";
import {RoleComponent} from "./System/role/role.component";
import {WarehouseEntryNoticeComponent} from "./Material/warehouseEntryNotice/warehouseEntryNotice.component";
import {MaterialSubstitutionComponent} from "./Material/materialSubstitution/materialSubstitution.component";
import {MaterialSubstitutionAuditComponent} from "./Material/materialSubstitutionAudit/materialSubstitutionAudit.component";
import {MaterialSubstitutionQueryComponent} from "./Material/materialSubstitutionQuery/materialSubstitutionQuery.component";
import {DesignInputComponent} from "./Design/designInput/designInput.component";
import {DesignAuditComponent} from './Design/designAudit/designAudit.component';
import {PressurePartsReportComponent} from './Material/pressurePartsReport/pressurePartsReport.component';
import {MaterialDistributionLedgerComponent} from './Material/materialDistributionLedger/materialDistributionLedger.component';
import {TestParametersComponent} from './InspectionAndTest/testParameters/testParameters.component';
import {InspectionRecordsComponent} from './Material/inspectionRecords/inspectionRecords.component';
import {TestParametersAuditComponent} from './InspectionAndTest/testParametersAudit/testParametersAudit.component';
import {MeasuringInstrumentLedgerComponent} from './Instrument/measuringInstrumentLedger/measuringInstrumentLedger.component';
import {MeasuringInstrumentLedgerAuditComponent} from './Instrument/measuringInstrumentLedgerAudit/measuringInstrumentLedgerAudit.component';
import {MeasuringInstrumentLedgerQueryComponent} from './Instrument/measuringInstrumentLedgerQuery/measuringInstrumentLedgerQuery.component';
import {VacuumParameterComponent} from './InspectionAndTest/vacuumParameter/vacuumParameter.component';
import {ProductTestBoardDataRegistrationComponent} from './PhysicalAndChemical/productTestBoardDataRegistration/productTestBoardDataRegistration.component';
import {MeasuringInstrumentSettingComponent} from './Instrument/measuringInstrumentSetting/measuringInstrumentSetting.component';
import {ProductManufacturingParametersComponent} from './InspectionAndTest/productManufacturingParameters/productManufacturingParameters.component';
import {ProductTestBoardCommissionComponent} from './PhysicalAndChemical/productTestBoardCommission/productTestBoardCommission.component';
import {VacuumParameterAuditComponent} from './InspectionAndTest/vacuumParameterAudit/vacuumParameterAudit.component';
import {ProductTestBoardCommissionAuditComponent} from './PhysicalAndChemical/productTestBoardCommissionAudit/productTestBoardCommissionAudit.component';
import {ProductTestBoardCommissionQueryComponent} from './PhysicalAndChemical/productTestBoardCommissionQuery/productTestBoardCommissionQuery.component';

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
      },{
        path: 'profile', component: ProfileComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '个人资料'
        }
      },{
        path: 'system/user', component: UserComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '用户管理'
        }
      },{
        path: 'system/role', component: RoleComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '角色管理'
        }
      },{
        path: 'changePassword', component: ChangePasswordComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '修改密码'
        }
      },{
        path: 'material/warehouseEntryNotice', component: WarehouseEntryNoticeComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '入库通知单生成'
        }
      },{
        path: 'material/materialSubstitution', component: MaterialSubstitutionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料代用申请'
        }
      },{
        path: 'material/materialSubstitutionAudit', component: MaterialSubstitutionAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料代用审核'
        }
      },{
        path: 'material/materialSubstitutionQuery', component: MaterialSubstitutionQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '代用通知单查询'
        }
      },{
        path: 'design/input', component: DesignInputComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '设计参数输入'
        }
      },{
        path: 'design/audit', component: DesignAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '设计参数审核'
        }
      },{
        path: 'material/pressurePartsReport', component: PressurePartsReportComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '受压元件使用材料一览表'
        }
      },{
        path: 'material/distributionLedger', component: MaterialDistributionLedgerComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '材料发放台帐'
        }
      },{
        path: 'inspectionAndTest/testParameters', component: TestParametersComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '试压参数输入'
        }
      }
      ,{
        path: 'inspectionAndTest/testParametersAudit', component: TestParametersAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '试压参数审核'
        }
      }
      ,{
        path: 'inspectionAndTest/vacuumParameters', component: VacuumParameterComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '真空参数输入'
        }
      },{
        path: 'inspectionAndTest/vacuumParameterAudit', component: VacuumParameterAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '真空参数审核'
        }
      },{
        path: 'material/inspectionRecords', component: InspectionRecordsComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '检验记录报表'
        }
      },{
        path: 'instrument/measuringInstrumentLedger', component: MeasuringInstrumentLedgerComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '计量器具台帐输入'
        }
      },{
        path: 'instrument/measuringInstrumentLedgerAudit', component: MeasuringInstrumentLedgerAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '计量器具台帐审核'
        }
      },{
        path: 'instrument/measuringInstrumentLedgerQuery', component: MeasuringInstrumentLedgerQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '计量器具台帐查询'
        }
      },{
        path: 'instrument/measuringInstrumentSetting', component: MeasuringInstrumentSettingComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '设备计量台帐邮箱设置'
        }
      },{
        path: 'physicalAndChemical/productTestBoardDataRegistration', component: ProductTestBoardDataRegistrationComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板参数输入'
        }
      },{
        path: 'physicalAndChemical/productTestBoardCommission', component: ProductTestBoardCommissionComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板委托'
        }
      },{
        path: 'physicalAndChemical/productTestBoardCommissionAudit', component: ProductTestBoardCommissionAuditComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板审核'
        }
      },{
        path: 'physicalAndChemical/productTestBoardCommissionQuery', component: ProductTestBoardCommissionQueryComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品试板查询'
        }
      },{
        path: 'inspectionAndTest/productManufacturingParameters', component: ProductManufacturingParametersComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '产品制造参数输入'
        }
      },
      {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
      {path: '404', component: Exception404Component}
    ]
  },
  {path: '**', redirectTo: '/404', pathMatch: 'full'}
];
