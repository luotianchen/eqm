import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressTestReportComponent} from "./pressTestReport.component";

@NgModule({
  declarations: [
    PressTestReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressTestReportComponent
  ]
})

export class pressTestReportModule {

}
