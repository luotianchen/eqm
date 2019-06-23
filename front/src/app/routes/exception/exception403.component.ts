import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-403',
  templateUrl: 'exception403.component.html',
  styleUrls: ['./exception403.component.less']
})

export class Exception403Component implements OnInit {
  ngOnInit() {
  }
  constructor(public router: Router) {
  }
}
