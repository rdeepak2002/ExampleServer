package com.deepak.exampleserver.dao;

import com.deepak.exampleserver.dataobject.UserDO;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public interface UserDAO {

    List<UserDO> getUsers();

    List<UserDO> getUsersByFavoriteNumber(Long favoriteNumber);

    UserDO getUserById(UUID id);

    List<UserDO> getUsersByName(String name);

    void createUser(UserDO userDO);

    boolean updateUser(UserDO userDO);

    boolean deleteUser(UUID id);
}
