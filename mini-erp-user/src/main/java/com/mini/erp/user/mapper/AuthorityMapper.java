package com.mini.erp.user.mapper;

import com.mini.erp.user.pojo.AuthorityInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorityMapper implements ResultSetExtractor<List<AuthorityInfo>> {


    @Override
    public List<AuthorityInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<AuthorityInfo> authorities = new ArrayList<>();

        while (rs.next()) {
            var authorityInfo = new AuthorityInfo();
            authorityInfo.setAuthority(rs.getString("authority"));
            authorities.add(authorityInfo);
        }

        return authorities;
    }
}
