import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialReinspectionQueryComponent} from './materialReinspectionQuery.component';

@NgModule({
  declarations: [
    MaterialReinspectionQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialReinspectionQueryComponent
  ]
})

export class MaterialReinspectionQueryModule {

}
