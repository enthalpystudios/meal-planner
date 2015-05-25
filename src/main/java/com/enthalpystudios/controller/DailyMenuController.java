package com.enthalpystudios.controller;

import com.enthalpystudios.dao.DailyMenuDAO;
import com.enthalpystudios.domain.DailyMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dailyMenus")
public class DailyMenuController {
    @Autowired
    private DailyMenuDAO dailyMenuDAO;


    @RequestMapping(method = RequestMethod.GET)
    public List findDailyMenus() {
        return dailyMenuDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public DailyMenu addDailyMenu(@RequestBody DailyMenu dailyMenu) {
        dailyMenu.setId(null);
        return dailyMenuDAO.saveAndFlush(dailyMenu);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public DailyMenu updateDailyMenu(@RequestBody DailyMenu dailyMenu, @PathVariable Long id) {
        dailyMenu.setId(id);
        return dailyMenuDAO.saveAndFlush(dailyMenu);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDailyMenu(@PathVariable Long id) {
        dailyMenuDAO.delete(id);
    }


}
