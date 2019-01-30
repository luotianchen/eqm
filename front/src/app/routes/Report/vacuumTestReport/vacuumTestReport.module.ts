import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {VacuumTestReportComponent} from "./vacuumTestReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    VacuumTestReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    VacuumTestReportComponent
  ]
})

export class VacuumTestReportModule {

}
