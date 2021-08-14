import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn:"root"
})
export class UtilService{
    carouselBg:BehaviorSubject<boolean>; // index-1 for carousel when sidebar opens
    constructor(){
        this.carouselBg = new BehaviorSubject(false); 
    }

}