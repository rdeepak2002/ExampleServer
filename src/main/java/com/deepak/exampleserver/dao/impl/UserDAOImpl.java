package com.deepak.exampleserver.dao.impl;

import com.deepak.exampleserver.dao.UserDAO;
import com.deepak.exampleserver.dataobject.UserDO;
import com.google.common.base.Preconditions;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.deepak.exampleserver.dao.dbconstants.UserDBConstants.BIRTHDAY_FIELD;
import static com.deepak.exampleserver.dao.dbconstants.UserDBConstants.DATE_CREATED_FIELD;
import static com.deepak.exampleserver.dao.dbconstants.UserDBConstants.DATE_UPDATED_FIELD;
import static com.deepak.exampleserver.dao.dbconstants.UserDBConstants.EMAIL_FIELD;
import static com.deepak.exampleserver.dao.dbconstants.UserDBConstants.FAVORITE_NUMBER_FIELD;
import static com.deepak.exampleserver.dao.dbconstants.UserDBConstants.ID_FIELD;
import static com.deepak.exampleserver.dao.dbconstants.UserDBConstants.NAME_FIELD;

/**
 * Created by deepak on 6/3/17.
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    private final UserRowMapper userRowMapper;

    private static final String GET_USERS =
            "SElECT * " +
            "FROM user";
    @Override
    public List<UserDO> getUsers() {
        return jdbcTemplate.query(GET_USERS, new UserRowMapper());
    }

    private static final String GET_USERS_BY_FAVORITE_NUMBER =
            "SELECT * " +
            "FROM user " +
            "WHERE favorite_number=?";
    @Override
    public List<UserDO> getUsersByFavoriteNumber(Long favoriteNumber) {
        return jdbcTemplate.query(
                GET_USERS_BY_FAVORITE_NUMBER,
                new ArgumentPreparedStatementSetter(new Object[] {favoriteNumber}),
                userRowMapper);
    }


    private static final String GET_USER_BY_ID =
            "SELECT * " +
            "FROM user " +
            "WHERE id=?";
    @Override
    public UserDO getUserById(@NonNull UUID id) {
        List<UserDO> userDOList = jdbcTemplate.query(
                GET_USER_BY_ID,
                new ArgumentPreparedStatementSetter(new Object[] {id.toString()}),
                userRowMapper);
        if (userDOList == null || userDOList.isEmpty()) {
            return null;
        }  else {
            Preconditions.checkArgument(userDOList.size() == 1, "query returned more than one");
            return userDOList.get(0);
        }
    }

    private static final String GET_USERS_BY_NAME =
            "SELECT * " +
            "FROM user " +
            "WHERE name=?";
    @Override
    public List<UserDO> getUsersByName(@NonNull String name) {
        return jdbcTemplate.query(
                GET_USERS_BY_NAME,
                new ArgumentPreparedStatementSetter(new Object[] {name}),
                userRowMapper);
    }

    private static final String CREATE_USER =
            "INSERT INTO user('id','name','email','birthday','favorite_number','date_created','date_updated') " +
            "VALUES (?,?,?,?,?,?,?)";
    @Override
    public void createUser(@NonNull UserDO userDO) {
        UUID uuid = UUID.randomUUID();
        log.info("Generating uuid for user: ", uuid.toString());
        userDO.setId(uuid);
        Date createTime = new Date();
        userDO.setDateCreated(createTime.getTime());
        userDO.setDateUpdated(createTime.getTime());

        Object[] values = new Object[]
                {
                        userDO.getId(),
                        userDO.getName(),
                        userDO.getEmail(),
                        userDO.getBirthday(),
                        userDO.getFavoriteNumber(),
                        userDO.getDateCreated(),
                        userDO.getDateUpdated()
                };

        log.info("Creating User={}", userDO);
        int success = jdbcTemplate.update(CREATE_USER, values);

        Preconditions.checkArgument(success == 1, "failed to create user: " + userDO.getId());
    }

    private static final String UPDATE_USER =
            "UPDATE user " +
            "SET id=?, name=?, email=?, birthday=?, favorite_number=?, date_updated=? " +
            "WHERE id=?";
    @Override
    public boolean updateUser(@NonNull UserDO userDO) {
        Date updateTime = new Date();
        userDO.setDateUpdated(updateTime.getTime());
        Object[] values = new Object[]
                {
                        userDO.getId(),
                        userDO.getName(),
                        userDO.getEmail(),
                        userDO.getBirthday(),
                        userDO.getFavoriteNumber(),
                        userDO.getDateUpdated(),
                        userDO.getId()
                };

        log.info("Updating User={}", userDO);
        int success = jdbcTemplate.update(UPDATE_USER, values);
        return success == 1 ? true : false;
    }

    private static final String DELETE_USER =
            "DELETE FROM user " +
            "WHERE id=?";
    @Override
    public boolean deleteUser(UUID id) {
        int success = jdbcTemplate.update(
                DELETE_USER, id.toString());
        return success == 1 ? true : false;
    }

    public static class UserRowMapper implements RowMapper<UserDO>
    {
        @Override
        public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            UserDO.UserDOBuilder userDOBuilder = UserDO.builder()
                    .id(UUID.fromString(rs.getString(ID_FIELD)))
                    .name(rs.getString(NAME_FIELD))
                    .email(rs.getString(EMAIL_FIELD))
                    .favoriteNumber(rs.getLong(FAVORITE_NUMBER_FIELD))
                    .birthday(rs.getLong(BIRTHDAY_FIELD))
                    .dateCreated(rs.getLong(DATE_CREATED_FIELD))
                    .dateUpdated(rs.getLong(DATE_UPDATED_FIELD));
            return userDOBuilder.build();
        }
    }
}
