import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialSubstitutionAuditComponent} from "./materialSubstitutionAudit.component";

@NgModule({
  declarations: [
    MaterialSubstitutionAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialSubstitutionAuditComponent
  ]
})

export class MaterialSubstitutionAuditModule {

}
