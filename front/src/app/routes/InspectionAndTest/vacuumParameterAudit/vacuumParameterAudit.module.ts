import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {VacuumParameterAuditComponent} from "./vacuumParameterAudit.component";

@NgModule({
  declarations: [
    VacuumParameterAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    VacuumParameterAuditComponent
  ]
})

export class VacuumParameterAuditModule {

}
