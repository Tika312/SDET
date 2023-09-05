package apiTests;

import com.digitalnomads.api.controllers.CourseController;
import com.digitalnomads.api.controllers.UserController;
import org.testng.annotations.BeforeSuite;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.BASE_URL;

public class BaseApiTest {

   protected UserController userController;
   protected CourseController courseController;


    @BeforeSuite(alwaysRun = true)
    public void setUpControllers(){
        this.userController = new UserController(BASE_URL);
        this.courseController = new CourseController(BASE_URL);
    }
}
