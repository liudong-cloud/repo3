package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 根据Id删除角色
     * @param id
     */
    void deleteRoleById(String id);

    /**
     * 新建用户
     * @param role
     */
    void save(Role role);

    /**
     * 根据Id查找角色信息
     * @param id
     * @return
     */
    Role findByRoleId(String id);

    /**
     * 根据角色ID查询其他权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissions(String roleId);

    /**
     * 添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}
