import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PneumaticTestProcedureReportComponent} from "./pneumaticTestProcedureReport.component";

@NgModule({
  declarations: [
    PneumaticTestProcedureReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PneumaticTestProcedureReportComponent
  ]
})

export class PneumaticTestProcedureReportModule {

}
