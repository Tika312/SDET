package com.digitalnomads.demo;

import com.google.gson.Gson;
import org.testng.annotations.Test;

public class GsonDemo {

    @Test
    void jsonIntoJavaObject(){
        String requestBodyJSon = " {\n" +           // это у нас Json формат
                "    \"brand\": \"BMW\",\n" +
                "    \"doors\": \"2\"\n" +
                "}";
        Gson gson = new Gson();
        Car car = gson.fromJson(requestBodyJSon, Car.class); // Json формат превратили в класс Car, в обьект
        System.out.println(car);                                //Этот процесс называется Deserialization (Десериализация)
        System.out.println(car.getBrand());
        System.out.println(car.getDoors());
    }

    @Test
    void javaObjectIntoJSon(){

        Car subaru = new Car("Subaru imprezza", 4);

        Gson gson = new Gson();
        String subaruJSon = gson.toJson(subaru); // Обьект Java превратили в фомат JSon
        System.out.println(subaruJSon);  // Этот процесс называется Serialization (Сериализация)
    }
}
