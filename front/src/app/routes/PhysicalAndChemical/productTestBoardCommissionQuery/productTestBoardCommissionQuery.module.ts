import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardCommissionQueryComponent} from './productTestBoardCommissionQuery.component';

@NgModule({
  declarations: [
    ProductTestBoardCommissionQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardCommissionQueryComponent
  ]
})

export class ProductTestBoardCommissionQueryModule {

}
