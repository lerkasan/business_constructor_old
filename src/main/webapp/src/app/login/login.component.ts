import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'my-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor() {
  	
  }

  ngOnInit() {
    console.log('Hello Login');
  }

}