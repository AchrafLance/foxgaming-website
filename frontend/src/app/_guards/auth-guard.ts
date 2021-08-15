import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "../services/auth-service";



@Injectable({
    providedIn: "root"
})

export class AuthGuard implements CanActivate{

    constructor(private authService: AuthService, 
        private router: Router){

    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
           const currentToken = this.authService.accessTokenValue; 
           if(currentToken){
               return true; 
           }
           this.router.navigate(['/login']); 
           return false; 

        }
}