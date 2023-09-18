package com.digitalnomads.ui.elementspage;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Button extends BaseElementsPage{

    public SelenideElement button;

    public Button(String xpath) {
        this.button = $(By.xpath(xpath));
    }

    public Button(String tag,String text){
            this.button = $(By.xpath("//" + tag + "[normalize-space() ='" + text + "']"));
    }

    public Button click(){
    elementActions.clickTheButton(button);
    return this;
    }

}
