package com.example.demo.healthy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @AfterAll
    static void afterAll() {
        System.out.println("Alo alo done nhé");
    }

    @BeforeEach
    void setUp() {
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @Test
    void should_ReturnCorrectDietPlan_When_CorrectCoder() {
        Coder coder = new Coder(1.82, 75, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        DietPlan actual = dietPlanner.calculateDiet(coder);

       assertAll(
               () -> assertEquals(expected.getCalories(), actual.getCalories()),
               () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()),
               () -> assertEquals(expected.getFat(), actual.getFat()),
               () -> assertEquals(expected.getProtein(), actual.getProtein())
       );
    }
}