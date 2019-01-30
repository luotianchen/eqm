import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardDataRegistrationQueryComponent} from './productTestBoardDataRegistrationQuery.component';

@NgModule({
  declarations: [
    ProductTestBoardDataRegistrationQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardDataRegistrationQueryComponent
  ]
})

export class ProductTestBoardDataRegistrationQueryModule {

}
