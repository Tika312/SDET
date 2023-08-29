package apiTests.users;

import apiTests.BaseApiTest;
import com.digitalnomads.api.entities.User;
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
    void getAllUsersIntoUserClass(){
        User[] user = userController.getAllUsers().as(User[].class);
        System.out.println(Arrays.toString(user));
    }
}
