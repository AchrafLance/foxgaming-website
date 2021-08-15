import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {

  @Output() closeTeamsTab = new EventEmitter<boolean>(); 

  constructor() { }

  ngOnInit(): void {
  }

  onCloseTeamsTab(){
    this.closeTeamsTab.emit(false); 
  }

}
