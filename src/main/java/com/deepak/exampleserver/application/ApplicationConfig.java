package com.deepak.exampleserver.application;

import com.deepak.exampleserver.dao.UserDAO;
import com.deepak.exampleserver.dao.impl.UserDAOImpl;
import com.deepak.exampleserver.manager.UserManager;
import com.deepak.exampleserver.manager.impl.UserManagerImpl;
import com.deepak.exampleserver.properties.ExampleServerProps;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by deepak on 6/3/17.
 */
@Configuration
@PropertySource(value= {"classpath:exampleserver.properties"})
@Slf4j
public class ApplicationConfig {

    //beans
    @Bean
    UserDAOImpl.UserRowMapper userRowMapper() {
        return new UserDAOImpl.UserRowMapper();
    }

    @Bean
    UserDAO userDAO(JdbcTemplate jdbcTemplate,
                    UserDAOImpl.UserRowMapper userRowMapper) {
        return new UserDAOImpl(jdbcTemplate, userRowMapper);
    }

    @Bean
    UserManager userManager(UserDAO userDAO) {
        return new UserManagerImpl(userDAO);
    }

    @Autowired
    private Environment env;

    @Value("${init-db}")
    private String initDatabase;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        String driverName = env.getProperty(ExampleServerProps.DB_DRIVER_PROP);
        String url = env.getProperty(ExampleServerProps.DB_URL_CONNECTION_STRING);
        Preconditions.checkNotNull(driverName, "db driverName should not be null");
        Preconditions.checkNotNull(url, "db url should not be null");

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(driverName)
                .url(url)
                .build();
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        log.info("Initialize Database={}", initDatabase);
        boolean initDB = Boolean.parseBoolean(initDatabase);
        Preconditions.checkArgument(initDB == true, "initDatabase should be true");

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("schema.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(initDB);
        return dataSourceInitializer;
    }
}
