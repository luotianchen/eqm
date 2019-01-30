import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialReinspectionCommissionQueryComponent} from './materialReinspectionCommissionQuery.component';

@NgModule({
  declarations: [
    MaterialReinspectionCommissionQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialReinspectionCommissionQueryComponent
  ]
})

export class MaterialReinspectionCommissionQueryModule {

}
