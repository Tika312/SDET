package com.digitalnomads;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.eclipse.jetty.server.Authentication;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;


public class TestPage {

    SelenideElement usersButton = $(By.xpath("//div[@class='tl-bold-link']/a[normalize-space()='Users']"));
    List<SelenideElement>listOfUsers = $$(By.xpath("//tr[@role='row']"));

//    WebDriver driver = new ChromeDriver();
    @Test
    void enterWithCookiesSelenium(){
//        driver.get("https://github.com/Tika312");
//        Cookie cookie = new Cookie("user_session","mCUXV493DYP0Gb_VJ31pwtGEjfXwkI35QsqyN_1OUaWoSnZv");
//        driver.manage().addCookie(cookie);
//        driver.navigate().refresh();
    }

    @Test
    void enterWithCookiesSelenide(){
        Configuration.browser = "chrome";
        open("https://github.com/Tika312");
        Cookie cookie = new Cookie("user_session","mCUXV493DYP0Gb_VJ31pwtGEjfXwkI35QsqyN_1OUaWoSnZv");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        WebDriverRunner.getWebDriver().navigate().refresh();
    }

    @Test
    void enterTalentLMS(){
        open("https://tilek.talentlms.com/dashboard");
        Cookie cookie = new Cookie("PHPSESSID","elb~fh5sllmcdkbp94adf8v7kih180");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        usersButton.click();
        boolean result = listOfUsers.stream().anyMatch(e->e.getText().contains("T. Ka"));
        Assert.assertTrue(result);
        for (SelenideElement element: listOfUsers){
            System.out.println(element.getText());
        }

    }

}
