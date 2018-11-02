import {Component, ElementRef, OnInit} from '@angular/core';
import {SessionStorageService} from 'src/app/core/storage/storage.module';
import {DashboardService} from './dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['./dashboard.component.less'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  constructor(public _storage: SessionStorageService, private dashboard: DashboardService) {
  }

  ngOnInit() {
  }
}
