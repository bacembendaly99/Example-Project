import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FoodListComponent} from "./components/food-list/food-list.component";
import {FoodDetailsComponent} from "./components/food-details/food-details.component";
import {AddFoodComponent} from "./components/add-food/add-food.component";
import {FoodCartComponent} from "./components/food-cart/food-cart.component";

const routes: Routes = [
  { path: '', redirectTo: 'food-list', pathMatch: 'full' },
  { path: 'food-list', component: FoodListComponent },
  { path: 'food/:id', component: FoodDetailsComponent },
  { path: 'add', component: AddFoodComponent },
  { path: 'food-cart', component: FoodCartComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
