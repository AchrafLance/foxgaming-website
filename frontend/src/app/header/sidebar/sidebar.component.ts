import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @Output() closeSideBar = new EventEmitter<boolean>(); 
  loggedIn:boolean; 
  constructor(private authService: AuthService, private router:Router) { }

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
    this.closeSideBar.emit(false); 

  }

  onLogin(){
    this.router.navigate(["/login"]);
    this.closeSideBar.emit(false); 
  }

}
