import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "../services/auth-service";

@Injectable({
    providedIn: "root"
})
export class JwtInterceptor implements HttpInterceptor{
    constructor(private authService: AuthService, private router:Router){

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const currentToken = this.authService.accessTokenValue;
        if (currentToken !== null) {
          req = req.clone({
            setHeaders: {
              Authorization: `Bearer ${currentToken}`,
              'Content-Type': 'application/json'
          }
          });
        }
    
    
        return next.handle(req);
}
}