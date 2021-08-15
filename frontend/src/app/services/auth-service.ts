import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { LoginResponse } from "../models/LoginResponse";
import { LoginRequest } from "../models/LoginRequest";
import { API_URL } from "src/environments/environment";
import { RegisterRequest } from "../models/RegisterRequest";
import { RegisterResponse } from "../models/RegisterResponse";
import { catchError, map } from "rxjs/operators";
import { BehaviorSubject, Observable, of } from "rxjs";
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';


const LOGIN_URL = API_URL + '/auth/signin'; 
const REGISTER_URL = API_URL + "/auth/signup"; 

@Injectable({
    providedIn: "root"
})
export class AuthService{

    loggedIn: BehaviorSubject<boolean>; 
    accessToken: BehaviorSubject<string>; 

    constructor(private http: HttpClient, private router: Router, 
        private toastrSerivce: ToastrService){
        this.loggedIn = new BehaviorSubject<boolean>(localStorage.getItem("accessToken") === null? false: true ); 
        this.accessToken = new BehaviorSubject<string>(localStorage.getItem("accessToken"))

    }

    get accessTokenValue(){
        return this.accessToken.value; 
    }

    login(loginRequest: LoginRequest){
        let loginResponse: LoginResponse = new LoginResponse(); 
        return this.http.post<LoginResponse>(LOGIN_URL, loginRequest).pipe(
            map(data =>{
                if(data != null && data.accessToken != null){
                    loginResponse = data; 
                    localStorage.setItem('accessToken', loginResponse.accessToken); 
                    this.loggedIn.next(true);  
                    this.accessToken.next(loginResponse.accessToken); 
                    return loginResponse; 
                }          
            }), 
            catchError(this.handleError('Username or password is invalid\nPlease try again!', null))
        )
        
        
    }

    logout(){
        localStorage.removeItem('accessToken'); 
        this.loggedIn.next(false);
        this.router.navigate(['/login']);
        this.accessToken.next(null); 
    }

    register(registerRequest: RegisterRequest){
        return this.http.post<RegisterResponse>(REGISTER_URL, registerRequest);
    }

    
    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
     private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            this.toastrSerivce.error(operation, "Login Failed")

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

}