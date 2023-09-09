package selenium.demo;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import user_interface.allure_report_listener.AllureReportListener;
import user_interface.config.FakeDataProvider;
import user_interface.helper.ElementActions;
import user_interface.drivers_factory.MainDriver;
import user_interface.pages.LoginPage;
import user_interface.pages.ProfilePage;


@Listeners(AllureReportListener.class)
public abstract class BaseTest {

    public WebDriver driver;
    public ElementActions elementActions;
    public FakeDataProvider fakeDataProvider;
    public LoginPage loginPage;
    public ProfilePage profilePage;

    @BeforeMethod
    public void setUpBrowser(){
        driver = MainDriver.getDriver();
        elementActions = new ElementActions();
        fakeDataProvider = new FakeDataProvider();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();

    }
    @AfterMethod
    public void tearDown(){
       MainDriver.closeDriver();
    }


}
