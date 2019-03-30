import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressVesShapeSizeInsReportComponent} from "./pressVesShapeSizeInsReport.component";

@NgModule({
  declarations: [
    PressVesShapeSizeInsReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressVesShapeSizeInsReportComponent
  ]
})

export class PressVesShapeSizeInsReportModule {

}
