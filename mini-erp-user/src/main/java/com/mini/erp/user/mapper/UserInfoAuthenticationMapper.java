package com.mini.erp.user.mapper;

import com.mini.erp.user.pojo.UserInfoAuthentication;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoAuthenticationMapper implements RowMapper<UserInfoAuthentication> {


    @Override
    public UserInfoAuthentication mapRow(ResultSet rs, int rowNum) throws SQLException {

        var user = new UserInfoAuthentication();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));

        return user;
    }
}
