import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WeldingDistributeComponent} from "./weldingDistribute.component";

@NgModule({
  declarations: [
    WeldingDistributeComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    WeldingDistributeComponent
  ]
})

export class WeldingDistributeModule {

}
