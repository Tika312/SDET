package apiTests.courses;

import apiTests.BaseApiTest;
import com.digitalnomads.api.asserts.ApiAssert;
import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.utils.EntityManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CourseApiTest extends BaseApiTest {

    @Test
    void getAllCourses() {
        courseController.getAllCourses();
        courseController.getNumberOfCourses();
    }

    @Test
    void getCourseById() {
        courseController.getCourseById("132");
        Course course = (Course) courseController.extractObjectFromResponse(Course.class);
        ApiAssert.assertThat(courseController.response)
                .isCorrectStatusCode(200);
    }

    @Test
    void getListOfUsers() {
        courseController.getListOfUsers("132");
        courseController.getNumberOfUsers("132");
    }

    @Test
    void getUserStatus() {
//        courseController.getUserStatusCourse("132","1");
        System.out.println(courseController.getNumberOfCourses());
    }

    @Test
    void createCourse() {
        Course expectedCourse = EntityManager.generateCourse();
        courseController.createCourse(expectedCourse);
        ApiAssert.assertThat(courseController.response)
                .isCorrectStatusCode(200)
                .assertCourse()
                .isEqual(expectedCourse)
                .isIdNotNull();
    }

    @Test
    void deleteCourse() {
        courseController.deleteCourse("140");
        ApiAssert.assertThat(courseController.response)
                .assertCourse()
                .isDeleted("Operation completed successfully ");

    }

    @Test
    void checkCourseLimit() {
        boolean totalCourses = courseController.checkCoursesLimit();
        if (totalCourses) {
            Course[] allCourses = courseController.getAllCourses().as(Course[].class);
            String courseIdToBeDeleted = allCourses[0].getId();
            String expectedResult = "Operation completed successfully ";
            String actualResult = courseController.deleteCourse(courseIdToBeDeleted);
            Assert.assertEquals(actualResult, expectedResult);
        } else {
            Course newCourse = EntityManager.generateCourse();
            courseController.createCourse(newCourse);
            ApiAssert.assertThat(courseController.response)
                    .isCorrectStatusCode(200)
                    .assertCourse()
                    .isIdNotNull()
                    .isEqual(newCourse);
        }
    }

    @Test
    void addUserToCourse(){
        courseController.addUserToCourse("29","136");
        ApiAssert.assertThat(courseController.response)
                .isCorrectStatusCode(200)
                .assertCourse()
                .isUserAddedToCourse("29","136","learner");
    }

}
