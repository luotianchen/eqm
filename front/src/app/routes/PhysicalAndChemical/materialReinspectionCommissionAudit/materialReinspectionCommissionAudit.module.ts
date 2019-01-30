import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialReinspectionCommissionAuditComponent} from './materialReinspectionCommissionAudit.component';

@NgModule({
  declarations: [
    MaterialReinspectionCommissionAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialReinspectionCommissionAuditComponent
  ]
})

export class MaterialReinspectionCommissionAuditModule {

}
