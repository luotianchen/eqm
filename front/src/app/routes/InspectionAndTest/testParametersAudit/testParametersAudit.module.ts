import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {TestParametersAuditComponent} from './testParametersAudit.component';

@NgModule({
  declarations: [
    TestParametersAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    TestParametersAuditComponent
  ]
})

export class TestParametersAuditModule {

}
