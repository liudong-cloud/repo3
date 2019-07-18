package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select r.* from role r ,users_role ur where r.id = ur.roleId and ur.userId = #{userId}")
    @Results(id = "roleMap" ,value = {
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions", javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.IPermissionDao.findById"))
    })
    List<Role> findById(String userId);

    @Select("select * from role")
    @ResultMap("roleMap")
    List<Role> findAll();

    @Delete("delete from role_permission where RoleId=#{id}")
    void deleteRole_permissionById(String id);

    @Delete("delete from users_role where RoleId=#{id}")
    void deleteUsers_roleById(String id);

    @Delete("delete from role where id=#{id}")
    void deleteRoleById(String id);

    @Insert("insert into role (roleName,roleDesc)values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{roleId}")
    @ResultMap("roleMap")
    Role findByRoleId(String roleId);

    @Select(" select * from permission where id  not in (select permissionId from role_permission where roleId = #{roleId}) ")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
