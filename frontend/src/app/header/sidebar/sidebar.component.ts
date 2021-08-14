import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @Output() closeSideBar = new EventEmitter<boolean>(); 
  loggedIn:boolean; 
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.loggedIn.subscribe(data =>{
      this.loggedIn = data; 
    })
  }

  onCloseSideBar(){
    this.closeSideBar.emit(false); 
  
  }

  onLogout(){
    this.authService.logout(); 
  }

}
