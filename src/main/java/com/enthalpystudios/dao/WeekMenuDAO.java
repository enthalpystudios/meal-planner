package com.enthalpystudios.dao;


import com.enthalpystudios.domain.WeekMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekMenuDAO extends JpaRepository<WeekMenu, Long> {
}
