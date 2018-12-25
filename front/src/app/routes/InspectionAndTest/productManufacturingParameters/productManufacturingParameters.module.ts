import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductManufacturingParametersComponent} from './productManufacturingParameters.component';

@NgModule({
  declarations: [
    ProductManufacturingParametersComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductManufacturingParametersComponent
  ]
})

export class ProductManufacturingParametersModule {

}
