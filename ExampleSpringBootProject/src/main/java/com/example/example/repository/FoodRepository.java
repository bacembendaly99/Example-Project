package com.example.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.example.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByAdded(boolean published);
    List<Food> findByNameContaining(String title);
}