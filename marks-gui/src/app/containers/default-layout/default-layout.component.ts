import {Component } from '@angular/core';
import { navItems } from '../../_nav';
import {Router} from "@angular/router";
import {LoginService} from "../../views/login/login.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;
  public navItems = navItems;

  constructor(private loginService: LoginService, private router: Router) {
  }

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  logout(){
    this.loginService.logout();
    this.router.navigate(["/login"]);
  }

  viewManual() {
    this.router.navigate(['/manual']);
  }
}
