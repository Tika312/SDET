package apiTests.users;

import apiTests.BaseApiTest;
import com.digitalnomads.api.asserts.ApiAssert;
import com.digitalnomads.api.entities.User;
import com.digitalnomads.api.utils.EntityManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class UserGetTest extends BaseApiTest {

    @Test
    public void getAllUsers(){
        userController.getAllUsers();
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
}
