package com.enthalpystudios.dao;


import com.enthalpystudios.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishDAO extends JpaRepository<Dish, Long> {
}
