package com.deepak.exampleserver.controllers;

import com.deepak.exampleserver.dto.UserRequestResponse;
import com.deepak.exampleserver.manager.UserManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by deepak on 6/3/17.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserManager userManager;

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public UserRequestResponse getUser(@NonNull @PathVariable String id) {
        log.info("Getting user with id={}", id);
        return userManager.readById(UUID.fromString(id));
    }
}
