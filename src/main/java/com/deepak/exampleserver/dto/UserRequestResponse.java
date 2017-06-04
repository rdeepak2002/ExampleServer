package com.deepak.exampleserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
@Data
@Builder
public class UserRequestResponse {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "favorite_number")
    private Long favoriteNumber;

    @JsonProperty(value = "birthday")
    private Date birthday;
}
