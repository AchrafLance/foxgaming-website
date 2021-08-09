import { Component, OnInit } from '@angular/core';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  itemsInCart: number; 
  showTeamsModal: boolean =false; 
  showShopModal: boolean = false; 
  showMediaModal: boolean = false; 
  showCompanyModal: boolean = false; 
  constructor(private shopService: ShopService) { }

  ngOnInit(): void {
    this.shopService.currentCartCount.subscribe(data => {
      this.itemsInCart = data;   
      })
    }
  

  teamsModal(){
    this.showTeamsModal = !this.showTeamsModal; 
    console.log(this.showTeamsModal);
  }
  

}
