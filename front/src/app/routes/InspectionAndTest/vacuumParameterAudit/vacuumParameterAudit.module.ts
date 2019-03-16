import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {VacuumParameterAuditComponent} from "./vacuumParameterAudit.component";
import {NgxXLSXModule} from "@notadd/ngx-xlsx";

@NgModule({
  declarations: [
    VacuumParameterAuditComponent
  ],
  imports: [
    SharedModule.forRoot(),
    NgxXLSXModule
  ],
  exports: [
    VacuumParameterAuditComponent
  ]
})

export class VacuumParameterAuditModule {

}
