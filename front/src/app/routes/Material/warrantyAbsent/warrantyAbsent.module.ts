import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WarrantyAbsentComponent} from "./warrantyAbsent.component";

@NgModule({
  declarations: [
     WarrantyAbsentComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    WarrantyAbsentComponent
  ]
})

export class WarrantyAbsentModule {

}
