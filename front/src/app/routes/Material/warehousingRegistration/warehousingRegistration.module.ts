import {NgModule} from '@angular/core';
import {WarehousingRegistrationComponent} from './warehousingRegistration.component';
import {SharedModule} from '../../../shared.module';

@NgModule({
  declarations: [WarehousingRegistrationComponent],
  imports: [
    SharedModule
  ],
  exports: [WarehousingRegistrationComponent]
})

export class WarehousingRegistrationModule {

}
