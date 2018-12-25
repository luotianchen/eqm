import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialSubstitutionComponent} from "./materialSubstitution.component";

@NgModule({
  declarations: [
    MaterialSubstitutionComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialSubstitutionComponent
  ]
})

export class MaterialSubstitutionModule {

}
