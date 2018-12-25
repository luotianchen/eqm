import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardCommissionComponent} from "./productTestBoardCommission.component";

@NgModule({
  declarations: [
    ProductTestBoardCommissionComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardCommissionComponent
  ]
})

export class ProductTestBoardCommissionModule {

}
