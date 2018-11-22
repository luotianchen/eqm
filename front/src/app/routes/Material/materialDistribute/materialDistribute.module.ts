import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialDistributeComponent} from "./materialDistribute.component";

@NgModule({
  declarations: [
    MaterialDistributeComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialDistributeComponent
  ]
})

export class MaterialDistributeModule {

}
