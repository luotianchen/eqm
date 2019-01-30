import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {LiquidPressTestReportComponent} from "./liquidPressTestReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    LiquidPressTestReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    LiquidPressTestReportComponent
  ]
})

export class LiquidPressTestReportModule {

}
