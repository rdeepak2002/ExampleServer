package com.deepak.exampleserver.dao.impl;

import com.deepak.exampleserver.dao.UserDAO;
import com.deepak.exampleserver.dataobject.UserDO;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public List<UserDO> getAllUsers() {
        return null;
    }

    @Override
    public List<UserDO> getAllUsersByFavoriteNumber(Long favoriteNumber) {
        return null;
    }

    @Override
    public UserDO readById(UUID id) {
        return null;
    }

    @Override
    public UserDO readByName(String name) {
        return null;
    }

    @Override
    public void createUser(UserDO userDO) {

    }

    @Override
    public boolean updateUser(UserDO userDO) {
        return false;
    }

    @Override
    public boolean deleteUser(UUID id) {
        return false;
    }
}
