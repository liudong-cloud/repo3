package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    /**
     * 查找所有用户
     * @return
     */
    public List<UserInfo> findAll();

    /**
     * 插入用户
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据Id查询用户信息
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 查找角色不具备的角色信息
     * @param uid
     * @return
     */
    List<Role> findOtherRoles(String uid);

    /**
     * 添加角色到用户
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds);
}
