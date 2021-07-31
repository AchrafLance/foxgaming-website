import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  showTeamsModal: boolean =false; 
  showShopModal: boolean = false; 
  showMediaModal: boolean = false; 
  showCompanyModal: boolean = false; 
  constructor() { }

  ngOnInit(): void {
  }

  teamsModal(){
    this.showTeamsModal = !this.showTeamsModal; 
    console.log(this.showTeamsModal);
  }
  

}
