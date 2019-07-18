package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select p.* from permission p,role_permission rp where p.id=rp.permissionId and rp.roleId=#{roleId}")
    List<Permission> findById(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id = #{pid}")
    Permission findByPid(String pid);

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteRole_permissionById(String id);

    @Delete("delete from permission where id=#{id}")
    void deletePermission(String id);
}

