import {Component, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {BreakpointObserver} from "@angular/cdk/layout";
import {delay} from "rxjs/operators";
import {AuthService} from "./services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ExampleAngularProject';
  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  admin=false;
  login=localStorage.getItem('STATE');
  role=localStorage.getItem('ROLE');
  authService:AuthService;
  router:Router;


  constructor(private observer: BreakpointObserver,authService:AuthService,router: Router) {
    this.authService=authService
    if (localStorage.getItem('ROLE')=="ROLE_ADMIN"){this.admin=true}
    console.log(localStorage.getItem('ROLE'))
    console.log(this.admin)
    this.router=router
    this.role=localStorage.getItem('ROLE');
    this.login=localStorage.getItem('STATE');

  }
  logout(){
    this.authService.logout()

    this.router.navigate(['login'])

    this.login='false'
  }

  ngAfterViewInit() {
    this.observer
      .observe(['(max-width: 800px)'])
      .pipe(delay(1))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });
  }
}
