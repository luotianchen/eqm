import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressTestReportComponent} from "./pressTestReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    PressTestReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    PressTestReportComponent
  ]
})

export class pressTestReportModule {

}
