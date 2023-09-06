package com.digitalnomads.api.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class MockData {
    private static final Faker faker = new Faker(new Locale("EN"));

    public static String generateName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateLogin() {
        return faker.name().username();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generatePassword() {
        return faker.internet().password(12, 16, true, true, true);
    }

    public static String generateCourseName(){
        return faker.educator().course();
    }

    public static String generateDescription(){
        return faker.educator().university();
    }

    public static String generateGroupName(){
        return faker.book().title();
    }

    public static String generateGroupDescription(){
        return faker.book().genre();
    }

    public static String generateGroupKey(){
        return faker.number().digits(8);
    }
    public static String generateGroupPrice(){
        return Integer.toString(faker.number().numberBetween(100,500));
    }
}
