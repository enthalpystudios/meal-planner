package com.enthalpystudios.controller;

import com.enthalpystudios.dao.DishVariationDAO;
import com.enthalpystudios.domain.Dish;
import com.enthalpystudios.domain.DishVariation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishVariations")
public class DishVariationController {
    @Autowired
    private DishVariationDAO dishVariationDAO;


    @RequestMapping(method = RequestMethod.GET)
    public List findDishVariations() {
        return dishVariationDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public DishVariation addDishVariation(@RequestBody DishVariation dishVariation) {
        dishVariation.setId(null);
        return dishVariationDAO.saveAndFlush(dishVariation);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public DishVariation updateDishVariation(@RequestBody DishVariation dishVariation, @PathVariable Long id) {
        dishVariation.setId(id);
        return dishVariationDAO.saveAndFlush(dishVariation);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDishVariation(@PathVariable Long id) {
        dishVariationDAO.delete(id);
    }

}
