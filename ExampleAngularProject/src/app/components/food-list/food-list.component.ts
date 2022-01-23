import { Component, OnInit } from '@angular/core';
import {FoodService} from "../../services/food.service";

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

  constructor(private foodService: FoodService) { }

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
