import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialReinspectionAuditComponent} from './materialReinspectionAudit.component';

@NgModule({
  declarations: [
    MaterialReinspectionAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialReinspectionAuditComponent
  ]
})

export class MaterialReinspectionAuditModule {

}
