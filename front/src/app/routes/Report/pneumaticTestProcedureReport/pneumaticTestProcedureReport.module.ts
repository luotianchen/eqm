import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PneumaticTestProcedureReportComponent} from "./pneumaticTestProcedureReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    PneumaticTestProcedureReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    PneumaticTestProcedureReportComponent
  ]
})

export class PneumaticTestProcedureReportModule {

}
