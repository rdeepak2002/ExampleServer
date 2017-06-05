package com.deepak.exampleserver.manager.impl;

import com.deepak.exampleserver.dao.UserDAO;
import com.deepak.exampleserver.dataobject.UserDO;
import com.deepak.exampleserver.dto.UserRequestResponse;
import com.deepak.exampleserver.manager.UserManager;
import com.google.common.base.Preconditions;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by deepak on 6/3/17.
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserManagerImpl implements UserManager {

    private final UserDAO userDAO;

    @Override
    public List<UserRequestResponse> getUsers() {
        return userDAO.getUsers().stream()
                .map(userDOtoDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRequestResponse> getUsersByFavoriteNumber(@NonNull Long favoriteNumber) {
        return userDAO.getUsersByFavoriteNumber(favoriteNumber).stream()
                .map(userDOtoDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public UserRequestResponse getUserById(@NonNull UUID id) {
        UserDO userDO = userDAO.getUserById(id);
        if (userDO == null) {
            return null;
        } else {
            return userDOtoDTOMapper.apply(userDO);
        }
    }

    @Override
    public List<UserRequestResponse> getUsersByName(@NonNull String name) {
        return userDAO.getUsersByName(name).stream()
                .map(userDOtoDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(@NonNull UserRequestResponse userDTO) {
        Preconditions.checkArgument(userDTO.getName() != null, "name should not be null");
        userDAO.createUser(userDTOtoDOMapper.apply(userDTO));
    }

    @Override
    public boolean updateUser(@NonNull UserRequestResponse userDTO) {
        return userDAO.updateUser(userDTOtoDOMapper.apply(userDTO));
    }

    @Override
    public boolean deleteUser(@NonNull UUID id) {
        return userDAO.deleteUser(id);
    }

    private static Function<UserDO, UserRequestResponse> userDOtoDTOMapper = userDO -> UserRequestResponse.builder()
            .id(userDO.getId())
            .name(userDO.getName())
            .email(userDO.getEmail())
            .birthday(userDO.getBirthday())
            .favoriteNumber(userDO.getFavoriteNumber())
            .dateCreated(userDO.getDateCreated())
            .dateUpdated(userDO.getDateUpdated())
            .build();

    private static Function<UserRequestResponse, UserDO> userDTOtoDOMapper = userRequestResponse -> UserDO.builder()
            .id(userRequestResponse.getId())
            .name(userRequestResponse.getName())
            .email(userRequestResponse.getEmail())
            .birthday(userRequestResponse.getBirthday())
            .favoriteNumber(userRequestResponse.getFavoriteNumber())
            .build();
}
