import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressurePartsAuditComponent} from "./pressurePartsAudit.component";

@NgModule({
  declarations: [
    PressurePartsAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressurePartsAuditComponent
  ]
})

export class PressurePartsAuditModule {

}
