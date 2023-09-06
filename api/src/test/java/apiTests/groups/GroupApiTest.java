package apiTests.groups;

import apiTests.BaseApiTest;
import com.digitalnomads.api.asserts.ApiAssert;
import com.digitalnomads.api.entities.Group;
import com.digitalnomads.api.utils.EntityManager;
import com.digitalnomads.api.utils.MockData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GroupApiTest extends BaseApiTest {

    @Test
    void getAllGroups(){
        Group[]groups = groupController.getAllGroups().as(Group[].class);
        System.out.println(Arrays.toString(groups));
    }
    @Test
    void getNumberOfGroups(){
        System.out.println(groupController.getNumberOfAllGroups());
    }

    @Test
    void getGroupById(){
        String expectedGroupId="2";
        groupController.getGroupById(expectedGroupId);
        Group actualGroup = (Group) groupController.extractObjectFromResponse(Group.class);
        Assert.assertEquals(actualGroup.getId(), expectedGroupId);
    }

    @Test
    void getListOfUsers(){
       groupController.getListOfUsersInThisGroup("2");
    }
    @Test
    void getListOfCourses(){
        groupController.getListOfCoursesInGroup("2");
    }

    @Test
    void createGroup(){
        String expectedGroupName = MockData.generateGroupName();
        String expectedGroupDescription = MockData.generateGroupDescription();
        groupController.createGroup(expectedGroupName,expectedGroupDescription);
        Group actualGroup = (Group) groupController.extractObjectFromResponse(Group.class);
        ApiAssert.assertThat(groupController.getResponse())
                .isCorrectStatusCode(200)
                .assertGroup()
                .idIsNotNull();
        Assert.assertEquals(actualGroup.getName(), expectedGroupName);
        Assert.assertEquals(actualGroup.getDescription(),expectedGroupDescription);
    }

    @Test
    void deleteGroup(){
        String expectedMessage = "Operation completed successfully";
        groupController.deleteGroup("4");
        ApiAssert.assertThat(groupController.getResponse())
                .isCorrectStatusCode(200)
                .assertGroup()
                .isDeleted(expectedMessage);
    }

    @Test
    void addUserToGroup(){
        groupController.addUserToGroup("26","08467022");
        ApiAssert.assertThat(groupController.getResponse())
                .isCorrectStatusCode(200)
                .assertGroup()
                .isUserAddedToGroup("26");
    }

    @Test
    void removeUserFromGroup(){
        groupController.removeUserFromGroup("26","25");
        ApiAssert.assertThat(groupController.getResponse())
                .isCorrectStatusCode(200)
                .assertGroup()
                .isUserRemovedFromGroup("26","25");
    }

    @Test
    void addCourseToGroup(){
        groupController.addCourseToGroup("141","9");
        ApiAssert.assertThat(groupController.getResponse())
                .isCorrectStatusCode(200)
                .assertGroup()
                .isCourseAddedToGroup("141","9");
    }
}
