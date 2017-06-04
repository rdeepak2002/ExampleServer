package com.deepak.exampleserver.dao;

import com.deepak.exampleserver.dataobject.UserDO;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public interface UserDAO {

    List<UserDO> getAllUsers();

    List<UserDO> getAllUsersByFavoriteNumber(@NonNull Long favoriteNumber);

    UserDO readById(@NonNull UUID id);

    UserDO readByName(@NonNull String name);

    void createUser(@NonNull UserDO userDO);

    boolean updateUser(@NonNull UserDO userDO);

    boolean deleteUser(@NonNull UUID id);
}
