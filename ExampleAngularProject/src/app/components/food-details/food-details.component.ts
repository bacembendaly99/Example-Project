import { Component, OnInit } from '@angular/core';
import {FoodService} from "../../services/food.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Food} from "../../shared/food.interface";

@Component({
  selector: 'app-food-details',
  templateUrl: './food-details.component.html',
  styleUrls: ['./food-details.component.css']
})
export class FoodDetailsComponent implements OnInit {

  currentFood: Food | any;
  message = '';

  constructor(
    private foodService: FoodService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    this.message = '';
    this.getFood(this.route.snapshot.paramMap.get('id'));
  }

  getFood(id : any): void {
    this.foodService.get(id)
      .subscribe(
        data => {
          this.currentFood = data;
          console.log(this.currentFood);
        },
        error => {
          console.log(error);
        });

  }

  updateAdded(status : any): void {
    const data = {
      name: this.currentFood.name,
      description: this.currentFood.description,
      added: status
    };

    this.foodService.update(this.currentFood.id, data)
      .subscribe(
        response => {
          this.currentFood.added = status;
          console.log(response);
        },
        error => {
          console.log(error);
        });


  }

  updateFood(): void {
    this.foodService.update(this.currentFood.id, this.currentFood)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'The Food was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  deleteFood(): void {
    this.foodService.delete(this.currentFood.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/food-list']);
        },
        error => {
          console.log(error);
        });
  }
}
