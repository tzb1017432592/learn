package com.example.lambda.mylambda.myinterface.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final Integer calories;
    private final String type;
    public Dish(String name, boolean vegetarian, Integer calories, String type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }
}
