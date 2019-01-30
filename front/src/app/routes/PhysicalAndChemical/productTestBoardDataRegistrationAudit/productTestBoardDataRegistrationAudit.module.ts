import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardDataRegistrationAuditComponent} from './productTestBoardDataRegistrationAudit.component';

@NgModule({
  declarations: [
    ProductTestBoardDataRegistrationAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardDataRegistrationAuditComponent
  ]
})

export class ProductTestBoardDataRegistrationAuditModule {

}
