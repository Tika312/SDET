package com.digitalnomads.foodpage;

import com.codeborne.selenide.CollectionCondition;

import com.digitalnomads.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class FoodPageTest extends BaseTest {



    @Test
    void foodPageFirstCuisineTest() {
        homePage.openNambaHomePage()
                .clickFoodButton()
                .clickFirstCuisine();
    }

    @Test
    void checkNumberOfCuisines() {
        homePage.openNambaHomePage()
                .clickFoodButton();
        int actualList = foodPage.listOfCuisine.size();
        Assert.assertEquals(actualList, 15);
    }

    @Test
    void checkNameOfCuisines() {
        homePage.openNambaHomePage()
                .clickFoodButton();
        String nationalCuisine = "НАЦИОНАЛЬНАЯ КУХНЯ";
        String chineseCuisine = "ТАЙСКАЯК34543543";
        boolean result = foodPage.listOfCuisine.stream().anyMatch(element -> element.getText().contains(nationalCuisine));
        boolean result1 = foodPage.listOfCuisine.stream().anyMatch(element -> element.getText().contains(chineseCuisine));
        Assert.assertTrue(result);
        Assert.assertTrue(result1);
    }

    @Test
    void selenideTest(){
        open("https://nambafood.kg/");
        $(By.xpath(  "//a[@class='menu-link '][@href='/food']")).click();
        $$(By.xpath("//div[@class='cat-wrap']/a")).get(0).click();
    }

    @Test
    void selenideTestCheckSize(){
        open("https://nambafood.kg/");
        $(By.xpath(  "//a[@class='menu-link '][@href='/food']")).click();
        $$(By.xpath("//div[@class='cat-wrap']/a")).shouldHave(CollectionCondition.size(15));
    }

    @Test
    void selenideTestCheckText(){
       homePage.openNambaHomePage()
               .clickFoodButton();
       $(foodPage.nationalCuisine).shouldHave(foodPage.css("font-size", "16px"));
    }
}

