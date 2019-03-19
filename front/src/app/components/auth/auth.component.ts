import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-auth',
  templateUrl: 'auth.component.html',
  styleUrls: ['./auth.component.less']
})

export class AuthComponent {
  @Output('OnAuthChange') onAuthSubmit:EventEmitter<any> = new EventEmitter();
  validateForm: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  onchange(){
    this.onAuthSubmit.emit(this.validateForm)
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      userName: [ null, [ Validators.required ] ],
      password: [ null, [ Validators.required ] ]
    });
  }
}
