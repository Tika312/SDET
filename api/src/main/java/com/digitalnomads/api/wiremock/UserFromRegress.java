package com.digitalnomads.api.wiremock;

import com.digitalnomads.api.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFromRegress extends BaseEntity {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String first_name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String last_name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String login;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String password;
}
