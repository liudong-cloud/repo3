package com.itheima.service.impl;

import com.itheima.dao.IRoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleDao.findAll();
        return roles;
    }

    @Override
    public void deleteRoleById(String id) {
        roleDao.deleteUsers_roleById(id);
        roleDao.deleteRole_permissionById(id);
        roleDao.deleteRoleById(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findByRoleId(String id) {
       Role role= roleDao.findByRoleId(id);
        return role;
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        List<Permission> permissions= roleDao.findOtherPermissions(roleId);
        return permissions;
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
