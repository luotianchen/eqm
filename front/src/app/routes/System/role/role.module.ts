import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {RoleComponent} from "./role.component";

@NgModule({
  declarations: [
     RoleComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    RoleComponent
  ]
})

export class RoleModule {

}
