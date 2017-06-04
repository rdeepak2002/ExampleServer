package com.deepak.exampleserver.manager.impl;

import com.deepak.exampleserver.dto.UserRequestResponse;
import com.deepak.exampleserver.manager.UserManager;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public class UserManagerImpl implements UserManager {

    @Override
    public List<UserRequestResponse> getAllUsers() {
        return null;
    }

    @Override
    public List<UserRequestResponse> getAllUsersByFavoriteNumber(Long favoriteNumber) {
        return null;
    }

    @Override
    public UserRequestResponse readById(UUID id) {
        return UserRequestResponse.builder()
                .id(id)
                .name("Test User")
                .birthday(new Date())
                .favoriteNumber(3L)
                .build();
    }

    @Override
    public UserRequestResponse readByName(String name) {
        return null;
    }

    @Override
    public void createUser(UserRequestResponse userDO) {

    }

    @Override
    public boolean updateUser(UserRequestResponse userDO) {
        return false;
    }

    @Override
    public boolean deleteUser(UUID id) {
        return false;
    }
}
