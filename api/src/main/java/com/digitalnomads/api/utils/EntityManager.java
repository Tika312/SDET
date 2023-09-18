package com.digitalnomads.api.utils;

import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.entities.Group;
import com.digitalnomads.api.entities.User;

public class EntityManager {
    public static User generateUser() {
        return User.builder()
                .firstName(MockData.generateName())
                .lastName(MockData.generateLastName())
                .email(MockData.generateEmail())
                .login(MockData.generateLogin())
                .password(MockData.generatePassword())
                .build();
    }

    public static Course generateCourse(){
        return Course.builder()
                .name(MockData.generateCourseName())
                .description(MockData.generateDescription())
                .build();
    }

    public static Group generateGroup(){
        return Group.builder()
                .name(MockData.generateGroupName())
                .description(MockData.generateGroupDescription())
//                .key(MockData.generateGroupKey())
//                .price(MockData.generateGroupPrice())
                .creatorId("1")
                .build();
    }

}
