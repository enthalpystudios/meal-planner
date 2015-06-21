package com.enthalpystudios.controller;

import com.enthalpystudios.Application;
import com.enthalpystudios.dao.DishVariationDAO;
import com.enthalpystudios.domain.Dish;
import com.enthalpystudios.domain.DishVariation;
import com.enthalpystudios.domain.DishVariationType;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DishVariationControllerTest {
    @Autowired
    private DishVariationDAO dao;

    @Value("${local.server.port}")
    int port;

    DishVariation spanishOmelette;
    DishVariation spanishOmeletteModified;
    DishVariation portugueseCod;
    String path = "/dishVariations";

    @Before
    public void setUp() throws Exception {
        spanishOmelette = createDishVariation("Spanish omelette", "SpanishOmelette", DishVariationType.BASIC);
        spanishOmeletteModified = createDishVariation("Tortilla", "SpanishOmelette", DishVariationType.BASIC);
        portugueseCod = createDishVariation("Portuguese cod", "PortugueseCod", DishVariationType.BASIC);

        //  The database is cleared and re-initialized for each test so that we always validate against a known state.
        dao.deleteAll();

        RestAssured.port = port;
    }

    @Test
    public void shouldFindDishVariations() throws Exception {
        // given
        dao.save(Arrays.asList(spanishOmelette, portugueseCod));

        when()
                .get(path)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("type", hasItems(DishVariationType.BASIC.name(), DishVariationType.BASIC.name()))
                .body("dish.name", hasItems(spanishOmelette.getDish().getName(), portugueseCod.getDish().getName()))
                .body("dish.shortName", hasItems(spanishOmelette.getDish().getShortName(), portugueseCod.getDish().getShortName()));
    }

    @Test
    public void shouldAddDishVariation() throws Exception {
        given()
                .body(spanishOmelette)
                .contentType(ContentType.JSON)
        .when()
                .post(path)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("type", Matchers.is(DishVariationType.BASIC.name()))
                .body("dish.name", Matchers.is(spanishOmelette.getDish().getName()))
                .body("dish.shortName", Matchers.is(spanishOmelette.getDish().getShortName()));
    }

    @Test
    public void shouldUpdateDishVariation() throws Exception {
        // given
        dao.save(Arrays.asList(spanishOmelette, portugueseCod));

        given()
                .body(spanishOmeletteModified)
                .contentType(ContentType.JSON)
        .when()
                .put(path + "/{id}", spanishOmelette.getId())
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("type", Matchers.is(DishVariationType.BASIC.name()))
                .body("dish.name", Matchers.is(spanishOmeletteModified.getDish().getName()))
                .body("dish.shortName", Matchers.is(spanishOmeletteModified.getDish().getShortName()))
                .body("id", Matchers.is(spanishOmelette.getId().intValue())); // JSON long are parsed to Integer
    }

    @Test
    public void shouldDeleteDishVariation() throws Exception {
        // given
        dao.save(Arrays.asList(spanishOmelette, portugueseCod));

        Long spanishOmeletteId = spanishOmelette.getId();
        when()
                .delete(path + "/{id}", spanishOmeletteId)
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    private DishVariation createDishVariation(String shortName, String name, DishVariationType variationType) {
        DishVariation dishVariation = new DishVariation();
        dishVariation.setType(variationType);
        dishVariation.setDish(createDish(shortName, name));
        return dishVariation;
    }

    private Dish createDish(String shortName, String name) {
        Dish dish = new Dish();
        dish.setShortName(shortName);
        dish.setName(name);
        return dish;
    }
}