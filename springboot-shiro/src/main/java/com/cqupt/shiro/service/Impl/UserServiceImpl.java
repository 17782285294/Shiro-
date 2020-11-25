package com.cqupt.shiro.service.Impl;

import com.cqupt.shiro.dao.UserDao;
import com.cqupt.shiro.entity.Permission;
import com.cqupt.shiro.entity.Role;
import com.cqupt.shiro.entity.User;
import com.cqupt.shiro.service.UserService;
import com.cqupt.shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public void register(User user) {
        // 明文密码进行 MD5 + Salt + Hash散列
        // 1、生成随机盐
        String salt = SaltUtils.getSalt(8);
        // 2、将随机盐保存到数据
        user.setSalt(salt);
        // 3、明文密码进行md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public List<Role> findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }

    @Override
    public List<Permission> findPermsByRoleId(int id) {
        return userDao.findPermsByRoleId(id);
    }
}
