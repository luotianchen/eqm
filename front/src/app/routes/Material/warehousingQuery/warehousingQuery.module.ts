import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WarehousingQueryComponent} from "./warehousingQuery.component";

@NgModule({
  declarations: [
     WarehousingQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    WarehousingQueryComponent
  ]
})

export class WarehousingQueryModule {

}
