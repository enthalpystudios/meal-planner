package com.enthalpystudios.controller;

import com.enthalpystudios.Application;
import com.enthalpystudios.dao.DishDAO;
import com.enthalpystudios.domain.Dish;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DishControllerTest {
    @Autowired
    private DishDAO dao;

    @Value("${local.server.port}")
    int port;

    Dish spanishOmelette;
    Dish spanishOmeletteModified;
    Dish portugueseCod;
    String path = "/dishes";

    @Before
    public void setUp() throws Exception {
        spanishOmelette = createDish("Spanish omelette", "SpanishOmelette");
        spanishOmeletteModified = createDish("Tortilla", "SpanishOmelette");
        portugueseCod = createDish("Portuguese cod", "PortugueseCod");

        //  The database is cleared and re-initialized for each test so that we always validate against a known state.
        dao.deleteAll();

        RestAssured.port = port;
    }


    private Dish createDish(String shortName, String name) {
        Dish dish = new Dish();
        dish.setShortName(shortName);
        dish.setName(name);
        return dish;
    }

    @Test
    public void shouldFindDish() throws Exception {
        // given
        dao.save(Arrays.asList(spanishOmelette, portugueseCod));

        when()
                .get(path)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", hasItems(spanishOmelette.getName(), portugueseCod.getName()))
                .body("shortName", hasItems(spanishOmelette.getShortName(), portugueseCod.getShortName()));
    }

    @Test
    public void shouldAddDish() throws Exception {
        given()
                .body(spanishOmelette)
                .contentType(ContentType.JSON)
        .when()
                .post(path)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.is(spanishOmelette.getName()))
                .body("shortName", Matchers.is(spanishOmelette.getShortName()));
    }

    @Test
    public void shouldUpdateDish() throws Exception {
        // given
        dao.save(Arrays.asList(spanishOmelette, portugueseCod));

        given()
            .body(spanishOmeletteModified)
            .contentType(ContentType.JSON)
        .when()
                .put(path + "/{id}", spanishOmelette.getId())
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.is(spanishOmeletteModified.getName()))
                .body("shortName", Matchers.is(spanishOmeletteModified.getShortName()))
                .body("id", Matchers.is(spanishOmelette.getId().intValue())); // JSON long are parsed to Integer
    }

    @Test
    public void shouldDeleteDish() throws Exception {
        // given
        dao.save(Arrays.asList(spanishOmelette, portugueseCod));

        Long spanishOmeletteId = spanishOmelette.getId();
        when()
                .delete(path + "/{id}", spanishOmeletteId)
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }
}