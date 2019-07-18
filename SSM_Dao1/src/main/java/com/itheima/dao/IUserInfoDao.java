package com.itheima.dao;


import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IUserInfoDao {

    @Select("select * from users where username=#{username}")
    @Results(id = "userMap",value = {
            @Result(id = true ,column = "id", property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles" ,javaType = java.util.List.class,
            many = @Many(select = "com.itheima.dao.IRoleDao.findById"))
    })
    UserInfo loadUserByUsername(String username);


    @Select("select * from users")
    @ResultMap("userMap")
    List<UserInfo> findAll();

    @Insert("insert into users (email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);


    @Select("select * from users where id=#{id}")
    @ResultMap("userMap")
    UserInfo findById(String id);

    @Select("select * from role where id  not in (select roleId from users_role where userId = #{uid}) ")
    List<Role> findOtherRoles(String uid);

    @Insert("insert into users_role (userId,roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
