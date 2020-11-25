package com.cqupt.shiro.dao;

import com.cqupt.shiro.entity.Permission;
import com.cqupt.shiro.entity.Role;
import com.cqupt.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    void save(User user);

    User findByUserName(String username);

    List<Role> findRolesByUserName(String username);

    List<Permission> findPermsByRoleId(int id);
}
