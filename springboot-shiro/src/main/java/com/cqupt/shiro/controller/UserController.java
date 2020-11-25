package com.cqupt.shiro.controller;

import com.cqupt.shiro.entity.User;
import com.cqupt.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            System.out.println("注册成功！");
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }

    /**
     * 用来处理身份认证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            System.out.println("登录成功！");
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！");
        }
        return "redirect:/login.jsp";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();  // 退出用户
        System.out.println("用户退出！");
        return "redirect:/login.jsp";
    }

    @RequiresRoles({"admin", "user"})  // 同时具有admin user 才能访问
    @RequiresPermissions("user:save:*")
    @RequestMapping("save")
    public String save() {
        System.out.println("进入方法！");
        return "redirect:/login.jsp";
    }
}
