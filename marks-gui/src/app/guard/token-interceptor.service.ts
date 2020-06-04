import { Injectable, Injector } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoginService} from "../views/login/login.service";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private injector: Injector) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authService = this.injector.get(LoginService);
    let tokenizeReq = req.clone({
      setHeaders: {
        Authorization : `Bearer ${authService.getTokenFromLocalStorage()}`
      }
    })
    return next.handle(tokenizeReq);
  }
}
