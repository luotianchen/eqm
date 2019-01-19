import {NgModule} from '@angular/core';
import {ChangePasswordComponent} from './changePassword.component';
import {SharedModule} from '../../../shared.module';

@NgModule({
  declarations: [ChangePasswordComponent],
  imports: [
    SharedModule
  ],
  exports: [ChangePasswordComponent]
})

export class ChangePasswordModule {

}
