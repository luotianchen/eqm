import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {QuaPlanPressVesReportComponent} from "./quaPlanPressVesReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    QuaPlanPressVesReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    QuaPlanPressVesReportComponent
  ]
})

export class QuaPlanPressVesReportModule {

}
