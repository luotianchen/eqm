import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {HydraulicTestReportComponent} from "./hydraulicTestReport.component";

@NgModule({
  declarations: [
    HydraulicTestReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    HydraulicTestReportComponent
  ]
})

export class HydraulicTestReportModule {

}
