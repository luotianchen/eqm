import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {VacuumParameterComponent} from "./vacuumParameter.component";

@NgModule({
  declarations: [
    VacuumParameterComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    VacuumParameterComponent
  ]
})

export class VacuumParameterModule {

}
