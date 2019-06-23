import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-404',
  templateUrl: 'exception404.component.html',
  styleUrls: ['./exception404.component.less']
})

export class Exception404Component implements OnInit {
  ngOnInit() {
  }
  constructor(public router: Router) {
  }
}
