package com.deepak.exampleserver.application;

import com.deepak.exampleserver.dao.UserDAO;
import com.deepak.exampleserver.dao.impl.UserDAOImpl;
import com.deepak.exampleserver.manager.UserManager;
import com.deepak.exampleserver.manager.impl.UserManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by deepak on 6/3/17.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    UserDAO userDAO() {
        return new UserDAOImpl();
    }

    @Bean
    UserManager userManager() {
        return new UserManagerImpl();
    }
}
