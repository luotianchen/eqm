import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductManufacturingParametersAuditComponent} from './productManufacturingParametersAudit.component';

@NgModule({
  declarations: [
    ProductManufacturingParametersAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductManufacturingParametersAuditComponent
  ]
})

export class ProductManufacturingParametersAuditModule {

}
