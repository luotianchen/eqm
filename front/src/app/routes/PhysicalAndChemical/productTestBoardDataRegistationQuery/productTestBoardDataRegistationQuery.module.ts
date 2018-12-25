import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardDataRegistationQueryComponent} from './productTestBoardDataRegistationQuery.component';

@NgModule({
  declarations: [
    ProductTestBoardDataRegistationQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardDataRegistationQueryComponent
  ]
})

export class ProductTestBoardDataRegistationQueryModule {

}
