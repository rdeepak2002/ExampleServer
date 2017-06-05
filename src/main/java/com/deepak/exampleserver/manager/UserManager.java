package com.deepak.exampleserver.manager;

import com.deepak.exampleserver.dto.UserRequestResponse;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public interface UserManager {

    List<UserRequestResponse> getUsers();

    List<UserRequestResponse> getUsersByFavoriteNumber(Long favoriteNumber);

    UserRequestResponse getUserById(UUID id);

    List<UserRequestResponse> getUsersByName(String name);

    void createUser(UserRequestResponse userDTO);

    boolean updateUser(UserRequestResponse userDTO);

    boolean deleteUser(UUID id);
}
