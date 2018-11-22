import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialUsingComponent} from "./materialUsing.component";

@NgModule({
  declarations: [
    MaterialUsingComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialUsingComponent
  ]
})

export class MaterialUsingModule {

}
