package com.enthalpystudios.dao;


import com.enthalpystudios.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealDAO extends JpaRepository<Meal, Long> {
}
