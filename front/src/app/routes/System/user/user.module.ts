import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {UserComponent} from "./user.component";

@NgModule({
  declarations: [UserComponent],
  imports: [
    SharedModule
  ],
  exports: [UserComponent]
})

export class UserModule {

}
