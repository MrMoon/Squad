import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontEnd';



  classApplied = false;

  toggleClass() {
    this.classApplied = !this.classApplied;
  }

  nav=false;
  expand(){
    this.nav=true;
  }
  shrink(){
    this.nav=false;
  }
}
