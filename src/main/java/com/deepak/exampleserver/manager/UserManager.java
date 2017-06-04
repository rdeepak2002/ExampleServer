package com.deepak.exampleserver.manager;

import com.deepak.exampleserver.dto.UserRequestResponse;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public interface UserManager {

    List<UserRequestResponse> getAllUsers();

    List<UserRequestResponse> getAllUsersByFavoriteNumber(@NonNull Long favoriteNumber);

    UserRequestResponse readById(@NonNull UUID id);

    UserRequestResponse readByName(@NonNull String name);

    void createUser(@NonNull UserRequestResponse userDO);

    boolean updateUser(@NonNull UserRequestResponse userDO);

    boolean deleteUser(@NonNull UUID id);
}
