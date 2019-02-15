import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {EssenceNg2PrintModule} from 'essence-ng2-print';
import {PressVesShapeSizeInsReportComponent} from "./pressVesShapeSizeInsReport.component";

@NgModule({
  declarations: [
    PressVesShapeSizeInsReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    PressVesShapeSizeInsReportComponent
  ]
})

export class PressVesShapeSizeInsReportModule {

}
