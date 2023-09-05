package com.digitalnomads.api.asserts;

import com.digitalnomads.api.entities.User;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
@Slf4j
public class UserAssert {

    Response response;

    public UserAssert(Response response) {
        this.response = response;
    }

    public static UserAssert assertThat(Response response) {
        return new UserAssert(response);
    }

    public UserAssert isEqual(User expectedUser) {
        User actualUser = this.response.as(User.class);
        actualUser.isEqual(expectedUser);
        return this;
    }

    public UserAssert isIdNotNull() {
        User actualUser = this.response.as(User.class);
        Assertions.assertThat(actualUser.getId()).isNotNull();
        log.info("ID {} of user not NULL", actualUser.getId());
        return this;
    }
}
