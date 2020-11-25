<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1> 系统主页V0.1 </h1>
    <a href="${pageContext.request.contextPath}/user/logout">退出</a>
    <ul>

        <li><a href="">用户管理</a>
            <ul>
                <shiro:hasPermission name="user:add:*">
                    <li href="">添加</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:*">
                    <li href="">删除</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:*">
                    <li href="">修改</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:select:*">
                    <li href="">查询</li>
                </shiro:hasPermission>
            </ul>
        </li>
        <li><a href="">商品管理</a> </li>
        <shiro:hasRole name="admin">
            <li><a href="">订单管理</a> </li>
            <li><a href="">物流管理</a> </li>
        </shiro:hasRole>
    </ul>
</body>
</html>