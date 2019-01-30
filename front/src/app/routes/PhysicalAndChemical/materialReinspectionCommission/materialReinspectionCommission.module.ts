import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialReinspectionCommissionComponent} from "./materialReinspectionCommission.component";

@NgModule({
  declarations: [
    MaterialReinspectionCommissionComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialReinspectionCommissionComponent
  ]
})

export class MaterialReinspectionCommissionModule {

}
