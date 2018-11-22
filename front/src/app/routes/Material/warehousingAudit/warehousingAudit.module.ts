import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WarehousingAuditComponent} from "./warehousingAudit.component";

@NgModule({
  declarations: [
     WarehousingAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    WarehousingAuditComponent
  ]
})

export class WarehousingAuditModule {

}
