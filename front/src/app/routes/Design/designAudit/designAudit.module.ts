import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {DesignAuditComponent} from './designAudit.component';

@NgModule({
  declarations: [
    DesignAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    DesignAuditComponent
  ]
})

export class DesignAuditModule {

}
