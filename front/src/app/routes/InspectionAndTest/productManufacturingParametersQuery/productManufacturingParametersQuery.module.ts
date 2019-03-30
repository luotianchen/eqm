import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductManufacturingParametersQueryComponent} from './productManufacturingParametersQuery.component';

@NgModule({
  declarations: [
    ProductManufacturingParametersQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductManufacturingParametersQueryComponent
  ]
})

export class ProductManufacturingParametersQueryModule {

}
