package com.example.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/example")
public class FoodController {

    @Autowired
    FoodRepository foodRepository;


    @GetMapping("/food")
    public ResponseEntity<List<Food>> getAllFoods(@RequestParam(required = false) String name) {
        try {
            List<Food> foods = new ArrayList<Food>();

            if (name == null)
                foodRepository.findAll().forEach(foods::add);
            else
                foodRepository.findByNameContaining(name).forEach(foods::add);

            if (foods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(foods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") long id) {
        Optional<Food> foodData = foodRepository.findById(id);

        if (foodData.isPresent()) {
            return new ResponseEntity<>(foodData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/food")
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        try {
            Food _food = foodRepository
                    .save(new Food(food.getName(), food.getDescription(), false));
            return new ResponseEntity<>(_food, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") long id, @RequestBody Food food) {
        Optional<Food> foodData = foodRepository.findById(id);

        if (foodData.isPresent()) {
            Food _food = foodData.get();
            _food.setName(food.getName());
            _food.setDescription(food.getDescription());
            _food.setAdded(food.isAdded());
            return new ResponseEntity<>(foodRepository.save(_food), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<HttpStatus> deleteFood(@PathVariable("id") long id) {
        try {
            foodRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/food")
    public ResponseEntity<HttpStatus> deleteAllFoods() {
        try {
            foodRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/food/added")
    public ResponseEntity<List<Food>> findByAdded() {
        try {
            List<Food> foods = foodRepository.findByAdded(true);

            if (foods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(foods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}