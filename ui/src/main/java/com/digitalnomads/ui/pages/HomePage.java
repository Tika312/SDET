package com.digitalnomads.ui.pages;


import com.digitalnomads.ui.elementspage.Button;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.open;


public class HomePage extends BasePage{


    public Button foodButton = new Button("a","Food");
    public Button pharmacyButton = new Button("a","Pharmacy");
    public Button adultButton = new Button("//a[@class='menu-link '][@href='/adult?language=en']");



    public HomePage openNambaHomePage(){
        open("https://nambafood.kg/?language=en");
        return this;
    }

    public FoodPage clickFoodButton(){
        foodButton.click();// так как мы нажимаем food  он переходит в food page
        return new FoodPage();   // и мы уже можем использовать все методы и webelement которые находятся в FoodPage class
    }
    public AdultsPage clickAdultsButton(){
        adultButton.click();
        return new AdultsPage();
    }


}
