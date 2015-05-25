package com.enthalpystudios.controller;

import com.enthalpystudios.dao.MealDAO;
import com.enthalpystudios.domain.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {
    @Autowired
    private MealDAO mealDAO;


    @RequestMapping(method = RequestMethod.GET)
    public List findMeals() {
        return mealDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Meal addMeal(@RequestBody Meal meal) {
        meal.setId(null);
        return mealDAO.saveAndFlush(meal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Meal updateMeal(@RequestBody Meal meal, @PathVariable Long id) {
        meal.setId(id);
        return mealDAO.saveAndFlush(meal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMeal(@PathVariable Long id) {
        mealDAO.delete(id);
    }


}
