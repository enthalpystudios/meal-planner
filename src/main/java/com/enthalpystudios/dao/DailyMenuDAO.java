package com.enthalpystudios.dao;


import com.enthalpystudios.domain.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyMenuDAO extends JpaRepository<DailyMenu, Long> {
}
