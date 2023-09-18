package com.digitalnomads.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FoodPage extends BasePage{

    public SelenideElement nationalCuisine = $(By.xpath("//div[text()='National Cuisine']"));

    public ElementsCollection listOfCuisine = $$(By.xpath("//div[@class='cat-wrap']/a"));
    public ElementsCollection listOfCuisineNames = $$(By.xpath("//div[@class='cat-item--title']"));

    public FoodPage clickFirstCuisine(){//кликает из списка cuisine рандомный WebElement
        Random random = new Random();
        int num = random.nextInt(0,listOfCuisine.size());
        elementActions.scrollDownToClick( listOfCuisine.get(num));
        return this;
    }

    public  Condition css(String name,String value){
        return new Condition("css") {
            public boolean apply(WebElement element){
                return value.equals(element.getCssValue(name));
            }
        };
    }

}
