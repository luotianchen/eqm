import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {VacuumTestReportComponent} from "./vacuumTestReport.component";

@NgModule({
  declarations: [
    VacuumTestReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    VacuumTestReportComponent
  ]
})

export class VacuumTestReportModule {

}
