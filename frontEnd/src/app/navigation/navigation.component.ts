import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  nav = true;


  expand(){
    this.nav=true;
  }
  shrink(){
    this.nav=false;
  }

  classApplied = false;

  toggleClass() {
    this.classApplied = !this.classApplied;
  }

}
