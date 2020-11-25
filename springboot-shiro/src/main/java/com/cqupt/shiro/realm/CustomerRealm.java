package com.cqupt.shiro.realm;

import com.cqupt.shiro.entity.Permission;
import com.cqupt.shiro.entity.Role;
import com.cqupt.shiro.entity.User;
import com.cqupt.shiro.service.UserService;
import com.cqupt.shiro.utils.ApplicationContextUtils;
import com.cqupt.shiro.utils.MySimpleByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        // 角色权限的授予
        List<Role> roles = userService.findRolesByUserName(primaryPrincipal);
        if (!CollectionUtils.isEmpty(roles)) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            roles.forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                // 权限信息
                List<Permission> perms = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    perms.forEach(permission -> {
                        simpleAuthorizationInfo.addStringPermission(permission.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        // 从容器中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findByUserName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), new MySimpleByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
