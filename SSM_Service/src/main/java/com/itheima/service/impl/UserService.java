package com.itheima.service.impl;

import com.itheima.dao.IUserInfoDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Service("userService")
public class UserService implements IUserService {
    @Autowired
    private IUserInfoDao userInfoDao;
    @Autowired
    private BCryptPasswordEncoder bcp;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.loadUserByUsername(username);
        List<Role> roles = userInfo.getRoles();
       return new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus()==1,true,
                true,true,getAuthorities(roles));


    }

    List<SimpleGrantedAuthority> getAuthorities(List<Role> roles){
        List<SimpleGrantedAuthority> mList=new ArrayList<>();
        for (Role role : roles) {
            mList.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return mList;
    }


    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> list = userInfoDao.findAll();
        return list;
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bcp.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = userInfoDao.findById(id);
        return userInfo;
    }

    @Override
    public List<Role> findOtherRoles(String uid) {
        List<Role> roles=userInfoDao.findOtherRoles(uid);
        return roles;
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userInfoDao.addRoleToUser(userId ,roleId);
        }
    }
}
