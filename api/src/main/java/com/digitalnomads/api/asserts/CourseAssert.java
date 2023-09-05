package com.digitalnomads.api.asserts;

import com.digitalnomads.api.entities.Course;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
@Slf4j
public class CourseAssert {

    Response response;

    public CourseAssert(Response response) {
        this.response = response;
    }

    public static CourseAssert assertThat(Response response){
        return new CourseAssert(response);
    }

    public CourseAssert isEqual(Course expectedCourse){
        Course actualCourse = this.response.as(Course.class);
        actualCourse.isEqual(expectedCourse);
        return this;
    }

    public CourseAssert isIdNotNull() {
        Course actualCourse = this.response.as(Course.class);
        Assertions.assertThat(actualCourse.getId()).isNotNull();
        log.info("ID {} of Course not NULL", actualCourse.getId());
        return this;
    }

    public CourseAssert isDeleted(String expectedMessage){
        String actualMessage = this.response.jsonPath().getString("message");
        Assertions.assertThat(actualMessage).isEqualTo(actualMessage);
        log.info("Successfully deleted, Actual message: {}, Expected message: {}", actualMessage,expectedMessage);
        return this;
    }

    public CourseAssert isUserAddedToCourse(String expectedUserId,String expectedCourseId,String expectedRole){
        String actualUserId = this.response.jsonPath().getString("user_id");
        String actualCourseId = this.response.jsonPath().getString("course_id");
        String actualRole = this.response.jsonPath().getString("role");
        Assertions.assertThat(actualUserId).isEqualTo("["+expectedUserId+"]");
        Assertions.assertThat(actualCourseId).isEqualTo("["+expectedCourseId+"]");
        Assertions.assertThat(actualRole).isEqualTo("["+expectedRole+"]");
        log.info("Successfully added to this Course, User ID: {}, Course ID: {}, Role: {}"
        ,expectedUserId,expectedCourseId,expectedRole);
        return this;
    }


}
