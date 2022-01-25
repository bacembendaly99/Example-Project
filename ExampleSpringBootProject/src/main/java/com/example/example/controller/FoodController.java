package com.example.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.example.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.example.model.Food;
import com.example.example.repository.FoodRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/example")
public class FoodController {
    FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/food")
    public ResponseEntity<List<Food>> getAllFoods(@RequestParam(required = false) String name) {
        return this.foodService.getAllFoods(name);
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") long id) {
        return this.foodService.getFoodById(id);
    }

    @PostMapping("/food")
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        return this.foodService.createFood(food);
    }

    @PutMapping("/food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") long id, @RequestBody Food food) {
        return this.foodService.updateFood(id, food);
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<HttpStatus> deleteFood(@PathVariable("id") long id) {
        return this.foodService.deleteFood(id);
    }

    @DeleteMapping("/food")
    public ResponseEntity<HttpStatus> deleteAllFoods() {
        return foodService.deleteAllFoods();
    }

    @GetMapping("/food/added")
    public ResponseEntity<List<Food>> findByAdded() {
        return this.foodService.findByAdded();
    }
}