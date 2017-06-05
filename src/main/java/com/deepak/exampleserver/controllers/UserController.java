package com.deepak.exampleserver.controllers;

import com.deepak.exampleserver.dto.UserRequestResponse;
import com.deepak.exampleserver.manager.UserManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserManager userManager;

    @RequestMapping(path = "/user/id/{id}", method = RequestMethod.GET)
    public UserRequestResponse getUserById(@NonNull @PathVariable String id) {
        log.info("Getting user with id={}", id);
        return userManager.getUserById(UUID.fromString(id));
    }

    @RequestMapping(path = "/user/name/{name}", method = RequestMethod.GET)
    public List<UserRequestResponse> getUsersByName(@NonNull @PathVariable String name) {
        log.info("Getting users with name={}", name);
        return userManager.getUsersByName(name);
    }

    @RequestMapping(path = "/user/favorite_number/{favoriteNumber}", method = RequestMethod.GET)
    public List<UserRequestResponse> getUsersByFavoriteNumber(@NonNull @PathVariable Long favoriteNumber) {
        log.info("Getting users with favorite_number={}", favoriteNumber);
        return userManager.getUsersByFavoriteNumber(favoriteNumber);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<UserRequestResponse> getUsers() {
        log.info("Getting users");
        return userManager.getUsers();
    }

    @RequestMapping(path = "/user/create", method = RequestMethod.PUT, consumes = "application/json")
    public void createUser(@RequestBody UserRequestResponse user) {
        log.info("Creating user={}", user);
        userManager.createUser(user);
    }

    @RequestMapping(path = "/user/update", method = RequestMethod.POST, consumes = "application/json")
    public void updateUser(@RequestBody UserRequestResponse user) {
        log.info("Updating user={}", user);
        userManager.updateUser(user);
    }

    @RequestMapping(path = "/user/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@NonNull @PathVariable String id) {
        log.info("Deleting user with id={}", id);
        userManager.deleteUser(UUID.fromString(id));
    }
}
