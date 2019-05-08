import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DashboardModule} from './dashboard/dashboard.module';
import {HomeModule} from './home/home.module';
import {LoginModule} from './login/login.module';
import {routes} from './routes';
import {ExceptionModule} from './exception/exception.module';
import {SystemModule} from "./System/system.module";
import {ProfileModule} from "./Profile/profile.module";
import {ReportModule} from "./Report/report.module";
import {DesignModule} from "./Design/design.module";
import {MaterialModule} from "./Material/material.module";
import {InstrumentModule} from "./Instrument/instrument.module";
import {InspectionAndTestModule} from "./InspectionAndTest/inspectionAndTest.module";
import {PhysicalAndChemicalModule} from "./PhysicalAndChemical/physicalAndChemical.module";
@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true}),
  ],
  exports: [
    DashboardModule,
    HomeModule,
    LoginModule,
    ExceptionModule,
    MaterialModule,
    PhysicalAndChemicalModule,
    InspectionAndTestModule,
    InstrumentModule,
    DesignModule,
    SystemModule,
    ProfileModule,
    ReportModule
  ]
})

export class RoutesModule {

}
