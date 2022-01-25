package com.example.example.dataSeed;


import com.example.example.model.Food;
import com.example.example.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class FoodDataLoader implements CommandLineRunner {

    @Autowired
    FoodRepository foodRepository;


    @Override
    public void run(String... args) throws Exception {
        loadFoodData();
    }

    private void loadFoodData() {
        if (foodRepository.count() == 0) {
            Food food1 = new Food("pizza", "flattened disk of bread dough topped with tomato, mozzarella",false);
            Food food2 = new Food("pasta", "mixture formed into different shapes and then boiled",true);
            Food food3 = new Food("sandwich", "slices of meat, cheese, or other food placed between two slices of bread",false);
            foodRepository.save(food1);
            foodRepository.save(food2);
            foodRepository.save(food3);
        }
    }

}