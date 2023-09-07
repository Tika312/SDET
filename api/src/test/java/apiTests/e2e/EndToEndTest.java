package apiTests.e2e;

import apiTests.BaseApiTest;
import com.digitalnomads.api.asserts.ApiAssert;
import com.digitalnomads.api.entities.Category;
import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.entities.User;
import com.digitalnomads.api.utils.EntityManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseApiTest {

    @Test
    void endToEndTest() {
        User expectedUser = EntityManager.generateUser();
        userController.createNewUser(expectedUser);
        User actualUser = (User) userController.extractObjectFromResponse(User.class);
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200)
                .assertUser()
                .isEqual(expectedUser)
                .isIdNotNull();
        /** New user Created*/

        Category[] categories = categoryController.getAllCategories().as(Category[].class);
        String firstIdOfCategories = categories[0].getId();
        Course course = EntityManager.generateCourse();
        course.setCategoryId(firstIdOfCategories);
        courseController.createCourse(course);
        Course actualCourse = (Course) courseController.extractObjectFromResponse(Course.class);
        Assert.assertEquals(actualCourse.getCategoryId(), firstIdOfCategories);
        /** New Course created*/

        courseController.addUserToCourse(actualUser.getId(), actualCourse.getId());
        ApiAssert.assertThat(courseController.response)
                .isCorrectStatusCode(200)
                .assertCourse()
                .isUserAddedToCourse(actualUser.getId(), actualCourse.getId(), "learner");
        /** Newly created user added to newly created course*/

        String coursesOfUsers = userController.checkUsersCourses(actualUser.getId());
        String json = userController.convertToJSON(coursesOfUsers);
        Course coursesOfUser = userController.converJsonToCourse(json);
        Assert.assertEquals(actualCourse.getId(), coursesOfUser.getId());
        Assert.assertEquals(actualCourse.getName(), coursesOfUser.getName());
    }

    @Test
    void deleteOrCreateNewUser(){
        userController.createNewUserOrDelete();
    }

}
