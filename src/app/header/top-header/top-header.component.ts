import { Component, OnInit } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-top-header',
  templateUrl: './top-header.component.html',
  styleUrls: ['./top-header.component.css'],
  animations: [
    trigger('left-right', [
      state('right', style({
        marginLeft: "77%"

      })),
      state('left', style({
        marginLeft: "0"

      })),
      transition('right => left', [
        animate('15s 100ms')
      ]),
      transition('left => right', [
        animate('15s')
      ]),
    ])]
})
export class TopHeaderComponent implements OnInit {
  animate!: boolean; 

  constructor() { 
  }

  ngOnInit(): void {
    this.animate = false; 
 
  }
  changeState(){
    this.animate = !this.animate;
  }

}
