package com.cqupt.shiro.service;

import com.cqupt.shiro.entity.Permission;
import com.cqupt.shiro.entity.Role;
import com.cqupt.shiro.entity.User;

import java.util.List;

public interface UserService {
    //用户测试方法
    void register(User user);
    // 根据用户名查询用户
    User findByUserName(String username);
    // 根据用户名查询用户的所有角色
    List<Role> findRolesByUserName(String username);
    // 根据用户角色查询权限信息
    List<Permission> findPermsByRoleId(int id);
}
