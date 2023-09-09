package user_interface.pages;

import org.openqa.selenium.support.PageFactory;
import user_interface.drivers_factory.MainDriver;
import user_interface.helper.ElementActions;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(MainDriver.getDriver(), this);
    }

    public ElementActions elementActions = new ElementActions();


}
