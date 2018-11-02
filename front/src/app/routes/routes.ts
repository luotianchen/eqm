import {ModuleWithProviders} from '@angular/core';

import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from '../routes/home/home.component';
import {LoginComponent} from '../routes/login/login.component';
import {DashboardComponent} from '../routes/dashboard/dashboard.component';
import {CanAuthProvide} from '../core/services/auth.service';
import {Exception404Component} from './exception/exception404.component';

export const routes: Routes = [
  {path: 'login', component: LoginComponent, canLoad: [CanAuthProvide]},
  {
    path: '', component: HomeComponent, canActivate: [CanAuthProvide],
    children: [
      {
        path: 'dashboard', component: DashboardComponent, canActivate: [CanAuthProvide],
        data: {
          breadcrumb: '控制台'
        }
      },
      {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
      {path: '404', component: Exception404Component}
    ]
  },
  {path: '**', redirectTo: '/404', pathMatch: 'full'}
];

