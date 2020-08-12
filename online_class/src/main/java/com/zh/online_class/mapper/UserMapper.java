package com.zh.online_class.mapper;

import com.zh.online_class.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int save(User user);


    User findByPhone(@Param("phone") String phone);


    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String md5);


    User findByUserId(@Param("user_id") Integer userId);
}
