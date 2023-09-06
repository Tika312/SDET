package com.digitalnomads.api.asserts;

import com.digitalnomads.api.entities.Group;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
@Slf4j
public class GroupAssert {

    Response response;

    public GroupAssert(Response response) {
        this.response = response;
    }

    public static GroupAssert assertThat(Response response){
        return new GroupAssert(response);
    }

    public GroupAssert isEqual(Group expectedGroup){
        Group actualGroup = this.response.as(Group.class);
        actualGroup.isEqual(expectedGroup);
        return this;
    }

    public GroupAssert idIsNotNull(){
        Group actualGroup = this.response.as(Group.class);
        Assertions.assertThat(actualGroup.getId()).isNotNull();
        log.info("ID {} of Group not NULL", actualGroup.getId());
        return this;
    }

    public GroupAssert isDeleted(String expectedMessage){
        String actualMessage = this.response.jsonPath()
                .getString("message");
        Assertions.assertThat(actualMessage).isEqualTo(expectedMessage);
        log.info("Successfully deleted, Actual message: {}, Expected message: {}", actualMessage,expectedMessage);
        return this;
    }

    public GroupAssert isUserAddedToGroup(String expectedUserId){
        String actualUserId = this.response.jsonPath()
                .getString("user_id");
        String groupId = this.response.jsonPath()
                .getString("group_id");
        String groupName = this.response.jsonPath()
                .getString("group_name");
        log.info("User ID is equal, actual User ID: {}, expected User ID: {}", actualUserId,expectedUserId);
        log.info("Group ID is : {}",groupId);
        log.info("Group name is : {}",groupName);
        Assertions.assertThat(actualUserId).isEqualTo(expectedUserId);
        return this;
    }

    public GroupAssert isUserRemovedFromGroup(String expectedUserId,String expectedGroupId){
        String actualUserId = this.response.jsonPath().getString("user_id");
        String actualGroupId = this.response.jsonPath().getString("group_id");
        String groupName = this.response.jsonPath().getString("group_name");
        Assertions.assertThat(actualUserId).isEqualTo(expectedUserId);
        Assertions.assertThat(actualGroupId).isEqualTo(expectedGroupId);
        Assertions.assertThat(groupName).isNotNull();
        log.info("Successfully deleted: User ID {}, Group ID {}, Group name {}", actualUserId,actualGroupId,groupName);
        return this;
    }

    public GroupAssert isCourseAddedToGroup(String expectedCourseId,String expectedGroupId){
        String actualCourseId = this.response.jsonPath()
                .getString("course_id");
        String actualGroupId = this.response.jsonPath()
                .getString("group_id");
        String groupName = this.response.jsonPath()
                .getString("group_name");
       Assertions.assertThat(actualCourseId).isEqualTo(expectedCourseId);
       Assertions.assertThat(actualGroupId).isEqualTo(actualGroupId);
       Assertions.assertThat(groupName).isNotNull();
       log.info("Successfully is added to Group, Course ID {}, Group ID {}, Group name {}", actualCourseId
       ,actualGroupId,groupName);
        return this;
    }

}
