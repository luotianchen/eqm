import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DashboardModule} from './dashboard/dashboard.module';
import {HomeModule} from './home/home.module';
import {LoginModule} from './login/login.module';
import {routes} from './routes';
import {ExceptionModule} from './exception/exception.module';

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true}),
  ],
  exports: [
    DashboardModule,
    HomeModule,
    LoginModule,
    ExceptionModule
  ]
})

export class RoutesModule {

}
