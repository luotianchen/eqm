import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressVesProdDataReportComponent} from "./PressVesProdDataReport.component";

@NgModule({
  declarations: [
    PressVesProdDataReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressVesProdDataReportComponent
  ]
})

export class PressVesProdDataReportReportModule {

}
