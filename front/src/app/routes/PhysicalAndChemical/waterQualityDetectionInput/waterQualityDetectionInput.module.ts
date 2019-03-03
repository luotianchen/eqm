import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WaterQualityDetectionInputComponent} from "./waterQualityDetectionInput.component";

@NgModule({
  declarations: [
    WaterQualityDetectionInputComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    WaterQualityDetectionInputComponent
  ]
})

export class WaterQualityDetectionInputModule {

}
