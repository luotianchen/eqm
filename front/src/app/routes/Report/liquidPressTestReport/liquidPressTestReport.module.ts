import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {LiquidPressTestReportComponent} from "./liquidPressTestReport.component";

@NgModule({
  declarations: [
    LiquidPressTestReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    LiquidPressTestReportComponent
  ]
})

export class LiquidPressTestReportModule {

}
