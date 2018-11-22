import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {SingleWarrantyAbsentComponent} from "./singleWarrantyAbsent.component";

@NgModule({
  declarations: [
     SingleWarrantyAbsentComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    SingleWarrantyAbsentComponent
  ]
})

export class SingleWarrantyAbsentModule {

}
