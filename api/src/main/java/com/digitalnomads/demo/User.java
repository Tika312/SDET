package com.digitalnomads.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;
}
