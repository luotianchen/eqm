import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared.module';
import {AuthComponent} from "./auth.component";

@NgModule({
  declarations: [
    AuthComponent
  ],
  imports: [SharedModule],
  exports: [AuthComponent]
})

export class AuthModule {
  constructor() {
  }
}
