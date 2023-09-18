//package com.digitalnomads.ui.helper;
//
//import com.digitalnomads.ui.drivers.MainDriver;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.WebDriver;
//
//
//public class AlertHelper {
//
//    private WebDriver driver = MainDriver.getDriver();
//
//    public AlertHelper (WebDriver driver){
//        this.driver = driver;
//    }
//
//    public Alert switchToAlert(){
//        return driver.switchTo().alert();
//    }
//    public void acceptAlert(){
//        switchToAlert().accept();
//
//    }
//
//    public void dissmisAlert(){
//        switchToAlert().dismiss();
//    }
//
//    public void sendKeysToAlert(String txt){
//        switchToAlert().sendKeys(txt);
//    }
//    public void getTextFromAlert(){
//        System.out.println(driver.switchTo().alert().getText());
//
//    }
//
//}
