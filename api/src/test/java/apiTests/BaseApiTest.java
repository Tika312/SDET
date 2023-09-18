package apiTests;

import com.digitalnomads.api.controllers.CategoryController;
import com.digitalnomads.api.controllers.CourseController;
import com.digitalnomads.api.controllers.GroupController;
import com.digitalnomads.api.controllers.UserController;
import org.testng.annotations.BeforeSuite;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.BASE_URL;

public class BaseApiTest {

   protected UserController userController;
   protected CourseController courseController;
   protected GroupController groupController;
   protected CategoryController categoryController;


    @BeforeSuite(alwaysRun = true)
    public void setUpControllers(){
        this.userController = new UserController(BASE_URL);
        this.courseController = new CourseController(BASE_URL);
        this.groupController = new GroupController(BASE_URL);
        this.categoryController = new CategoryController(BASE_URL);
    }
}
