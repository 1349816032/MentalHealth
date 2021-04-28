package com.zyf.mental_health.mapper;

import com.zyf.mental_health.data.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoMapper implements RowMapper<UserInfo> {
    @Override
    public UserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(resultSet.getLong("user_id"));
        userInfo.setUserName(resultSet.getString("user_name"));
        userInfo.setUserPassword(resultSet.getString("user_password"));

        return userInfo;
    }
}
