package com.example.example.service;

import com.example.example.model.Food;
import com.example.example.repository.FoodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public ResponseEntity<List<Food>> getAllFoods(String name) {
        try {
            List<Food> foods = new ArrayList<Food>();

            if (name == null) foodRepository.findAll().forEach(foods::add);
            else foodRepository.findByNameContaining(name).forEach(foods::add);

            if (foods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(foods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Food> getFoodById(long id) {
        Optional<Food> foodData = foodRepository.findById(id);

        if (foodData.isPresent()) {
            return new ResponseEntity<>(foodData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Food> createFood(Food food) {
        try {
            Food _food = foodRepository.save(new Food(food.getName(), food.getDescription(), false));
            return new ResponseEntity<>(_food, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Food> updateFood(long id, Food food) {
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

    public ResponseEntity<HttpStatus> deleteFood(long id) {
        try {
            foodRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllFoods() {
        try {
            foodRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
