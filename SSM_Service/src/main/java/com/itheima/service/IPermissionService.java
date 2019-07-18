package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionService {
    /**
     * 查找所有用户
     * @return
     */
    List<Permission> findAll();

    /**
     * 新建权限
     * @param permission
     */
    void save(Permission permission);

    /**
     * 根据Id查询权限
     * @param id
     * @return
     */
    Permission findByPid(String id);

    /**
     * 删除权限
     * @param id
     */
    void deletePermission(String id);
}
