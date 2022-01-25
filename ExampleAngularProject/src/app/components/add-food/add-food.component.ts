import { Component, OnInit } from '@angular/core';
import {FoodService} from "../../services/food.service";

@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent implements OnInit {
  food = {
    name: '',
    description: '',
    added: false
  };
  submitted = false;

  constructor(private foodService: FoodService) { }

  ngOnInit(): void {
  }

  saveFood(): void {
    const data = {
      name: this.food.name,
      description: this.food.description
    };

    this.foodService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newFood(): void {
    this.submitted = false;
    this.food = {
      name: '',
      description: '',
      added: false
    };
  }
}
