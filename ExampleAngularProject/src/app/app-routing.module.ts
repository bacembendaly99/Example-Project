import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FoodListComponent} from "./components/food-list/food-list.component";
import {FoodDetailsComponent} from "./components/food-details/food-details.component";
import {AddFoodComponent} from "./components/add-food/add-food.component";
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {AuthGuard} from "./guards/auth.guard";
import {UserRole} from "./shared/user-role.interface";

const routes: Routes = [

  {path: '', redirectTo: 'food-list', pathMatch: 'full'},
  {
    path: 'food-list', component: FoodListComponent, canActivate: [AuthGuard], data: {
      role: [UserRole.ROLE_USER, UserRole.ROLE_ADMIN]
    }
  },
  {
    path: 'food/:id',
    component: FoodDetailsComponent,
    canActivate: [AuthGuard],
    data: {role: [UserRole.ROLE_ADMIN, UserRole.ROLE_USER]}
  },
  {path: 'add', component: AddFoodComponent, canActivate: [AuthGuard], data: {role: [UserRole.ROLE_ADMIN]}},
  {path: 'login', component: LoginPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
