package com.enthalpystudios.dao;


import com.enthalpystudios.domain.DishVariation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishVariationDAO extends JpaRepository<DishVariation, Long> {
}
