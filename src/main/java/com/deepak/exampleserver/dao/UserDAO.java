package com.deepak.exampleserver.dao;

import com.deepak.exampleserver.dataobject.UserDO;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public interface UserDAO {

    List<UserDO> getUsers();

    List<UserDO> getUsersByFavoriteNumber(@NonNull Long favoriteNumber);

    UserDO getUserById(@NonNull UUID id);

    List<UserDO> getUsersByName(@NonNull String name);

    void createUser(@NonNull UserDO userDO);

    boolean updateUser(@NonNull UserDO userDO);

    boolean deleteUser(@NonNull UUID id);
}
