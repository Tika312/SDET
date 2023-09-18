package com.digitalnomads;




import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.SoftAsserts;
import com.digitalnomads.ui.config.ConfigReader;
import com.digitalnomads.ui.config.FakeDataProvider;
import com.digitalnomads.ui.helper.ElementActions;
import com.digitalnomads.ui.pages.FoodPage;
import com.digitalnomads.ui.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@Listeners({SoftAsserts.class})
public abstract class BaseTest {

    public ElementActions elementActions;
    public FakeDataProvider fakeDataProvider;
    public HomePage homePage;
    public FoodPage foodPage;



        @BeforeClass
          public void setUpBrowser() {
            elementActions = new ElementActions();
            fakeDataProvider = new FakeDataProvider();
            homePage = new HomePage();
            foodPage = new FoodPage();
        }

        @AfterClass
    public void clearCookiesAndStorage(){
           if (ConfigReader.CLEAR_COOKIES_AND_STORAGE){
               JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
               WebDriverRunner.getWebDriver().manage().deleteAllCookies();
               js.executeScript("window.sessionStorage.clear()");
           }
        }



}
