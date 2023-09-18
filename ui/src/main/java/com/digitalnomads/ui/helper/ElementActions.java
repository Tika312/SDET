package com.digitalnomads.ui.helper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;




public class ElementActions {




    public ElementActions clickTheButton(SelenideElement element){
        element.shouldHave(Condition.visible).click();
        return this;
    }

    public ElementActions writeText(SelenideElement element, String txt){
       element.shouldBe(Condition.visible).sendKeys(txt);
        return this;
    }

    public ElementActions scrollDownToClick(SelenideElement element){
        element.shouldBe(Condition.visible).scrollTo().click();
       return this;
    }

    public ElementActions clickAndFillUpAndTab(SelenideElement element, String text){
        element.shouldBe(Condition.visible).sendKeys(text);
        element.sendKeys(Keys.TAB);
        return this;
    }
    public ElementActions scrollDown(SelenideElement element){
        element.shouldBe(Condition.visible).scrollTo();
        return this;
    }

    public ElementActions clearAllEnterNewText(SelenideElement element, String txt) throws InterruptedException {
        element.shouldBe(Condition.visible);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(txt);
        element.sendKeys(Keys.ESCAPE);
        return this;
    }

    public ElementActions attachFile(SelenideElement element, String path){
        String filePath = path;
        element.shouldHave(Condition.visible);
        element.sendKeys(filePath);
        return this;
    }

    public ElementActions scrollDownToBottomPage(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    public ElementActions moveToElement(SelenideElement element){
        element.shouldBe(Condition.visible).hover();
        return this;
    }

}
