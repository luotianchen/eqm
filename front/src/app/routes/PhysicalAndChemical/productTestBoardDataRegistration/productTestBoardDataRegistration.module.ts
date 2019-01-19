import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardDataRegistrationComponent} from "./productTestBoardDataRegistration.component";

@NgModule({
  declarations: [
    ProductTestBoardDataRegistrationComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardDataRegistrationComponent
  ]
})

export class ProductTestBoardDataRegistrationModule {

}
