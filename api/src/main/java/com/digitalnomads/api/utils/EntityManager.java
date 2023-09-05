package com.digitalnomads.api.utils;

import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.entities.User;

import static com.digitalnomads.api.utils.MockData.*;

public class EntityManager {
    public static User generateUser() {
        return User.builder()
                .firstName(generateName())
                .lastName(generateLastName())
                .email(generateEmail())
                .login(generateLogin())
                .password(generatePassword())
                .build();
    }

    public static Course generateCourse(){
        return Course.builder()
                .name(generateCourseName())
                .description(generateDescription())
                .build();
    }

}
