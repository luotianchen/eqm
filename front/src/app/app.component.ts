import {Component, Renderer2} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  constructor(private renderer: Renderer2) {
    window.onload = () => {
      const ele = document.querySelector('#loaders');
      this.renderer.addClass(ele, 'hideSlow');
      setTimeout(
        ()=>this.renderer.addClass(ele,"hidden"),
        300
      )
    };
  }
}
