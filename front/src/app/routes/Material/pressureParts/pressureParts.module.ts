import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressurePartsComponent} from "./pressureParts.component";

@NgModule({
  declarations: [
    PressurePartsComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressurePartsComponent
  ]
})

export class PressurePartsModule {

}
