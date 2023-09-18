package com.digitalnomads.ui.helper;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class JavaScriptHelper {

    public JavaScriptHelper clickWithJs(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver();
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    public JavaScriptHelper highlightElementAfterClick(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver();
        clickWithJs(element);
        js.executeScript("arguments[0].style.backgroundColor = 'red'", element);
        return this;
    }

    public JavaScriptHelper triggerAlert (String txt){
        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver();
        js.executeScript("alert('" + txt + "')");
        return this;
    }
}
