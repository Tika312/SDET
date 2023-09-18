package com.digitalnomads.ui.pages.talentLMS;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.digitalnomads.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;

public class TalentLMSMainPage extends BasePage {
    SelenideElement usersButton = $(By.xpath("//div[@class='tl-bold-link']/a[normalize-space()='Users']"));
    SelenideElement addUserButton = $(By.xpath("//div[@class='hidden-phone']/a[normalize-space()='Add user']"));
    SelenideElement categoriesButton = $(By.xpath("//div[@class='tl-bold-link']/a[normalize-space()='Categories']"));
    SelenideElement addCategoryButton = $(By.xpath("//div[@class='hidden-phone']/a[normalize-space()='Add category']"));


    public TalentLMSMainPage openMainPage(){
        open("https://tilek.talentlms.com/dashboard");
        Cookie cookie = new Cookie("PHPSESSID","elb~5bjotrbb5p42s3j8tjbqdlp4a2");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        return this;
    }

    public TalentLMSMainPage clickUsers(){
        elementActions.clickTheButton(usersButton);
        return this;
    }

    public TalentLMSMainPage clickAddUsers(){
        elementActions.clickTheButton(addUserButton);
        return this;
    }

    public TalentLMSMainPage clickCategories(){
        elementActions.clickTheButton(categoriesButton);
        return this;
    }

    public TalentLMSMainPage clickAddCategories(){
        elementActions.clickTheButton(addCategoryButton);
        return this;
    }

    public TalentLMSMainPage returnBack(){
        back();
        return this;
    }

    public TalentLMSMainPage moveForward(){
        forward();
        return this;
    }



}
