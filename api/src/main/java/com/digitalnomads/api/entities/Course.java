package com.digitalnomads.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course extends BaseEntity{


    @JsonInclude(JsonInclude.Include.NON_NULL)
    String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "category_id")
    String categoryId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "creator_id")
    String creatorId;

}
