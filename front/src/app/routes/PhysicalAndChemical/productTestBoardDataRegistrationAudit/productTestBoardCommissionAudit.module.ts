import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardCommissionAuditComponent} from './productTestBoardCommissionAudit.component';

@NgModule({
  declarations: [
    ProductTestBoardCommissionAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardCommissionAuditComponent
  ]
})

export class ProductTestBoardCommissionAuditModule {

}
