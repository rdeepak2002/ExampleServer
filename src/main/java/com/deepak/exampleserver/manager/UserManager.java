package com.deepak.exampleserver.manager;

import com.deepak.exampleserver.dto.UserRequestResponse;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public interface UserManager {

    List<UserRequestResponse> getUsers();

    List<UserRequestResponse> getUsersByFavoriteNumber(@NonNull Long favoriteNumber);

    UserRequestResponse getUserById(@NonNull UUID id);

    List<UserRequestResponse> getUsersByName(@NonNull String name);

    void createUser(@NonNull UserRequestResponse userDTO);

    boolean updateUser(@NonNull UserRequestResponse userDTO);

    boolean deleteUser(@NonNull UUID id);
}
