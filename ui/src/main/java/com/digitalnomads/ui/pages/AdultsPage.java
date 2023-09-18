package com.digitalnomads.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class AdultsPage extends BasePage{

    public SelenideElement yesButton = $(By.xpath("//div[@class='order--btn order--access']"));
    public SelenideElement tobaccoAndHookah = $(By.xpath("//a[@class='home--cat-item food adult-tab-right']"));
    public List<SelenideElement> categoriesOfProducts = $$(By.xpath("//div[@class='shop-categories']/a"));
    public SelenideElement supermarkets = $(By.xpath("//div[text()='Supermarkets']"));

    public AdultsPage clickYesButton(){
        yesButton.click();
        return this;
    }

    public AdultsPage clickTobaccoAndHookah(){
        tobaccoAndHookah.click();
        return this;
    }

    public AdultsPage clickRandomOneOfCategories(){
        Random random = new Random();
        int randomCategory = random.nextInt(0,categoriesOfProducts.size());
        elementActions.scrollDownToClick(categoriesOfProducts.get(randomCategory));
        return this;
    }

    public  boolean containsText(SelenideElement element, String text){
        return element.getText().contains(text);
    }

}
