package apiTests;

import com.digitalnomads.api.controllers.UserController;
import org.testng.annotations.BeforeSuite;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.BASE_URL;

public class BaseApiTest {

   protected UserController userController;


    @BeforeSuite(alwaysRun = true)
    public void setUpControllers(){
        this.userController = new UserController(BASE_URL);
    }
}
