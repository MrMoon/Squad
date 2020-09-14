import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontEnd';

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
