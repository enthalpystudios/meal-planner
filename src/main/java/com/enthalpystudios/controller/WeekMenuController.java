package com.enthalpystudios.controller;

import com.enthalpystudios.dao.WeekMenuDAO;
import com.enthalpystudios.domain.WeekMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekMenus")
public class WeekMenuController {
    @Autowired
    private WeekMenuDAO weekMenuDAO;


    @RequestMapping(method = RequestMethod.GET)
    public List findWeekMenus() {
        return weekMenuDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public WeekMenu addWeekMenu(@RequestBody WeekMenu weekMenu) {
        weekMenu.setId(null);
        return weekMenuDAO.saveAndFlush(weekMenu);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public WeekMenu updateWeekMenu(@RequestBody WeekMenu weekMenu, @PathVariable Long id) {
        weekMenu.setId(id);
        return weekMenuDAO.saveAndFlush(weekMenu);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteWeekMenu(@PathVariable Long id) {
        weekMenuDAO.delete(id);
    }


}
