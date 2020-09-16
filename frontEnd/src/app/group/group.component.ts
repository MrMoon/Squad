import { Component, OnInit } from '@angular/core';
import { GroupService } from './group.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {
  groups;

  constructor(groupSer:GroupService) {
    this.groups=groupSer.getGroups(1);
   }

  ngOnInit(): void {
  }

}
