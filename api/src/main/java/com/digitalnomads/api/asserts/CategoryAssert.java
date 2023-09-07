package com.digitalnomads.api.asserts;

import com.digitalnomads.api.entities.Category;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;

@Slf4j
public class CategoryAssert {

    Response response;

    public CategoryAssert(Response response) {
        this.response = response;
    }

    public static CategoryAssert assertThat(Response response){
        return new CategoryAssert(response);
    }

    public CategoryAssert isIdEqual(String expectedId){
        Category actualCategory = this.response.as(Category.class);
        Assertions.assertThat(actualCategory.getId()).isEqualTo(expectedId);
        log.info("ID of Categories are equal, actual ID: {}, expected ID: {}", actualCategory.getId(), expectedId);
        return this;
    }

}
