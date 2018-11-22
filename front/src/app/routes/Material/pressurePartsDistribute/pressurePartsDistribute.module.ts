import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressurePartsDistributeComponent} from "./pressurePartsDistribute.component";

@NgModule({
  declarations: [
    PressurePartsDistributeComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressurePartsDistributeComponent
  ]
})

export class PressurePartsDistributeModule {

}
