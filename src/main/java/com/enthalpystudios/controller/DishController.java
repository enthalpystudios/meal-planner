package com.enthalpystudios.controller;

import com.enthalpystudios.dao.DishDAO;
import com.enthalpystudios.domain.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    private DishDAO dishDAO;


    @RequestMapping(method = RequestMethod.GET)
    public List findDishes() {
        return dishDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Dish addDish(@RequestBody Dish dish) {
        dish.setId(null);
        return dishDAO.saveAndFlush(dish);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Dish updateDish(@RequestBody Dish dish, @PathVariable Long id) {
        dish.setId(id);
        return dishDAO.saveAndFlush(dish);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDish(@PathVariable Long id) {
        dishDAO.delete(id);
    }

}
