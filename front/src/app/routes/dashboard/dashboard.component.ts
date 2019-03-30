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
  public grates = [
    ,
    "上午好！",
    "中午好！",
    "下午好！",
    "晚上好！"
  ]
  getGrate(){
    let day  = new Date();
    let time = day.getHours();
    let sentence = "";
    if(time<9 && time>=2)
      sentence = "早上好！";
    else if(time<12 && time>=10)
      sentence = "上午好！";
    else if(time==12)
      sentence = "中午好！";
    else if(time>=13 && time<=18)
      sentence = "下午好！";
    else
      sentence = "晚上好！";
    return sentence;
  }
  ngOnInit() {
  }
}
