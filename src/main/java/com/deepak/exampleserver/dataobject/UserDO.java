package com.deepak.exampleserver.dataobject;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
@Data
@Builder
public class UserDO {

    private UUID id;

    private String name;

    private String email;

    private Long favoriteNumber;

    private Long birthday;

    private Long dateCreated;

    private Long dateUpdated;
}
