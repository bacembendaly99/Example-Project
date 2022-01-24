import { Component, OnInit } from '@angular/core';
import {FoodService} from "../../services/food.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-foods-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit {

  foods: any;
  currentFood : any;
  currentIndex = -1;
  name = '';
  admin = false;


  constructor(private foodService: FoodService,private authService: AuthService) {
    if (localStorage.getItem('ROLE')=="ROLE_ADMIN"){this.admin=true}
    console.log(this.authService.role)

  }

  ngOnInit(): void {
    this.retrieveFoods();
  }

  retrieveFoods(): void {
    this.foodService.getAll()
      .subscribe(
          (data: any) => {
          this.foods = data;
          console.log(data);
        },
          (error: any) => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveFoods();
    this.currentFood = null;
    this.currentIndex = -1;
  }

  setActiveFood(food: null, index: number): void {
    this.currentFood = food;
    this.currentIndex = index;
  }

  removeAllFoods(): void {
    this.foodService.deleteAll()
      .subscribe(
          (response: any) => {
          console.log(response);
          this.retrieveFoods();
        },
          (error: any) => {
          console.log(error);
        });
  }

  searchName(): void {
    this.foodService.findByName(this.name)
      .subscribe(
          (data: any) => {
          this.foods = data;
          console.log(data);
        },
          (error: any) => {
          console.log(error);
        });
  }
}
