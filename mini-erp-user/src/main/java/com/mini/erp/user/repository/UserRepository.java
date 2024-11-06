package com.mini.erp.user.repository;


import com.mini.erp.user.mapper.AuthorityMapper;
import com.mini.erp.user.mapper.UserInfoAuthenticationMapper;

import com.mini.erp.user.pojo.AuthorityInfo;
import com.mini.erp.user.pojo.RegistrationRequest;
import com.mini.erp.user.pojo.UserInfoAuthentication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public RegistrationRequest registrationNewUser(RegistrationRequest request){

        String sql = "INSERT INTO users (email, password) VALUES (?,?)";

        int affectedRow = jdbcTemplate.update(sql, request.getEmail(), request.getPassword());

        if(affectedRow == 0){
            throw new IllegalStateException();
        }
        return request;
    }


    public UserInfoAuthentication findUserByEmail(String email){

        String sql = "SELECT email password, enabled FROM users WHERE enabled = true and email = ?";

        var user =  jdbcTemplate.queryForObject(sql, new UserInfoAuthenticationMapper(), email);

        return user;
    }

    public List<AuthorityInfo> getAuthoritiesByEmail(String email){


        String sql = "SELECT r.role_name as authority " +
                "FROM users_roles ur " +
                "JOIN roles r ON ur.role_id = r.id " +
                "JOIN users u ON ur.user_id = u.id " +
                "WHERE u.email = ?";

        return jdbcTemplate.query(sql, new AuthorityMapper(), email);
    }


}
