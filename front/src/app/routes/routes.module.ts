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
    PressurePartsDistributeModule
  ]
})

export class RoutesModule {

}
