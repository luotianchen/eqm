import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MonthMaterialComponent} from "./monthMaterial.component";

@NgModule({
  declarations: [
    MonthMaterialComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MonthMaterialComponent
  ]
})

export class MonthMaterialModule {

}
