package apiTests.users;

import apiTests.BaseApiTest;
import com.digitalnomads.api.asserts.ApiAssert;
import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.entities.User;
import com.digitalnomads.api.utils.EntityManager;
import com.digitalnomads.api.utils.MockData;
import com.digitalnomads.wiremock.UserFromRegress;
import com.digitalnomads.wiremock.WireMockTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class UserGetTest extends BaseApiTest {

    @Test
    public void getAllUsers() throws JsonProcessingException {
//        userController.getAllUsers();
       String response = userController.checkUsersCourses("21");
       String json = userController.convertToJSON(response);
       Course course = userController.converJsonToCourse(json);
        System.out.println(json);

    }
    @Test
    void getListOfCoursesOfUser(){
       Course[]courses = userController.getUserCourses("21");
        System.out.println(Arrays.toString(courses));
        String courseId = courses[0].getId();
        String courseName = courses[0].getName();
        System.out.println(courseId);
        System.out.println(courseName);

    }

    @Test
    public void getUserByEmail(){
        String expectedEmail = "legend@gmail.com";
        String expectedEmailNegative = "legend@gmail.com1";
        userController.getUserByEmail(expectedEmail);
        User user = (User) userController.extractObjectFromResponse(User.class);
        System.out.println(user.toString());
        Assert.assertEquals(user.getEmail(), expectedEmailNegative, "user email is not correct");
    }

    @Test
    void getUserById(){
        userController.getUserById("1");
        User expectedUser = (User) userController.extractObjectFromResponse(User.class);
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200)
                .assertUser()
                .isIdNotNull()
                .isEqual(expectedUser);
    }

    @Test
    void isUserOnline(){
        boolean expectedResult = true;
        boolean actualResult = userController.isUserOnline("1");
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    void getAllUsersIntoUserClass(){
        User[] user = userController.getAllUsers().as(User[].class);
        System.out.println(Arrays.toString(user));
    }

    @Test
    void createUser(){
        User expectedUser = EntityManager.generateUser();
        userController.createNewUser(expectedUser);
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200)
                .assertUser()
                .isEqual(expectedUser)
                .isIdNotNull();
    }

    @Test
    void deleteUser(){
        String expectedResult = "Operation completed successfully";
        String actualResult = userController.deleteUser("28");
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    void checkLimit(){
        boolean result = userController.checkUserLimit();
        if (result){
            String expectedResult = "Operation completed successfully";
            User[]totalUser = userController.getAllUsers().as(User[].class);
            String actualResult = userController.deleteUser(totalUser[0].getId());
            Assert.assertEquals(actualResult, expectedResult);
        }else {
            User newUser = EntityManager.generateUser();
            userController.createNewUser(newUser);
            ApiAssert.assertThat(userController.getResponse())
                    .isCorrectStatusCode(200)
                    .assertUser()
                    .isIdNotNull()
                    .isEqual(newUser);
        }
    }

    @Test
    void createNewUser(){
        WireMockTest wireMockTest = new WireMockTest();
        wireMockTest.setUpWireMock();
        wireMockTest.testWireMock();
        String response =  wireMockTest.response.jsonPath().getString("data");
        String json1 = userController.convertToJSONNew(response);
        System.out.println(json1);
        Gson gson = new Gson();
        UserFromRegress[]user = gson.fromJson(json1, UserFromRegress[].class);
        System.out.println(Arrays.toString(user));

        for (int i=0; i < 4; i++){
            user[i].setLogin(MockData.generateLogin());
            user[i].setPassword(MockData.generatePassword());
            user[i].setEmail(MockData.generateEmail());
            UserFromRegress user1 = userController.createNewUserFromReg(user[i]);

            Assert.assertEquals(user1.getFirst_name(), user[i].getFirst_name());
            Assert.assertEquals(user1.getLast_name(), user[i].getLast_name());
            Assert.assertEquals(user1.getLogin(), user[i].getLogin());
            Assert.assertEquals(user1.getEmail(), user[i].getEmail());
        }



    }

}
