import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import {AuthService} from '../service/auth.service';

@Injectable()
export class BoardAuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (this.authService.isLoggedAsAdmin()) {
      return true;
    }
    if (this.authService.isLoggedAsExpert()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }

}

