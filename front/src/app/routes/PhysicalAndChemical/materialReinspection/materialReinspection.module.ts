import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialReinspectionComponent} from "./materialReinspection.component";

@NgModule({
  declarations: [
    MaterialReinspectionComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialReinspectionComponent
  ]
})

export class MaterialReinspectionModule {

}
