import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {QuaPressVesPlanReportComponent} from "./quaPressVesPlanReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    QuaPressVesPlanReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    QuaPressVesPlanReportComponent
  ]
})

export class QuaPressVesPlanReportModule {

}
