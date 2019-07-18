package com.itheima.service.impl;

import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("permissionService")
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        List<Permission> list= permissionDao.findAll();
        return list;
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findByPid(String id) {
       Permission p=  permissionDao.findByPid(id);
        return p;
    }

    @Override
    public void deletePermission(String id) {
       permissionDao.deleteRole_permissionById(id);
       permissionDao.deletePermission(id);
    }
}
