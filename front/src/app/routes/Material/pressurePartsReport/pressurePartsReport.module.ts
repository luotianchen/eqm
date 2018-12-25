import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressurePartsReportComponent} from "./pressurePartsReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    PressurePartsReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    PressurePartsReportComponent
  ]
})

export class PressurePartsReportModule {

}
