import {Component, OnInit} from '@angular/core';
import {AuthService} from 'src/app/services/auth.service';
import {take} from "rxjs/operators";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  public loginValid = true;
  public username = '';
  public password = '';
  public test = '';

  constructor(private authservice: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.authservice.login(this.username, this.password).pipe(
      take(1)
    ).subscribe({
      next: (user) => {
        this.loginValid = true;
        localStorage.setItem('STATE', 'true');
        localStorage.setItem('ROLE', user.role);
        console.log(localStorage.getItem('STATE'));
        console.log(localStorage.getItem('ROLE'));
        this.authservice.role = user.role;
        this.authservice.isLogin = true;
        this.router.navigate(['/food-list'])


      },
      error: _ => this.loginValid = false
    });
  }
}

function delay(ms: number) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

