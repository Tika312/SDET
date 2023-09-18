package com.digitalnomads.adultpage;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.eclipse.jetty.server.Authentication;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        Cookie cookie = new Cookie("PHPSESSID","//elb~5bjotrbb5p42s3j8tjbqdlp4a2");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        usersButton.click();
        boolean result = listOfUsers.stream().anyMatch(e->e.getText().contains("T. Ka"));
        Assert.assertTrue(result);
        for (SelenideElement element: listOfUsers){
            System.out.println(element.getText());
        }
    }

    @Test
    void updateCookies() {
        open("https://tilek.talentlms.com/dashboard");
        Cookie cookie = new Cookie("PHPSESSID","//elb~5bjotrbb5p42s3j8tjbqdlp4a2");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();

// Продлите срок действия куки на 1 час
        for (Cookie cooki : cookies) {
            // Установите новое значение срока действия куки
            Date newExpiry = Date.from(Instant.now().plusSeconds(3600)); // 3600 секунд = 1 час
            cookie = new Cookie.Builder(cookie.getName(), cookie.getValue())
                    .domain(cookie.getDomain())
                    .path(cookie.getPath())
                    .expiresOn(newExpiry)
                    .isHttpOnly(cookie.isHttpOnly())
                    .isSecure(cookie.isSecure())
                    .build();
            // Обновите куки в браузере
            WebDriverRunner.getWebDriver().manage().addCookie(cookie);
            refresh();
        }
    }
}
