<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%@include file="/WEB-INF/jsp/common/css.jsp"%> <!-- 静态包含: 生成一个class  适合不总变的情况-->
    <link rel="stylesheet" href="${PATH}/static/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">CrowdFunding-产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form id="loginForm" class="form-signin" role="form" action="${PATH}/login" method="post">  <!-- action="doLogin" 请求DispatcherController中的doLogin方法 -->
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <!-- 登陆的信息显示出来 -->
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}"> <!-- 请求域中若不为空 取出来 -->
            <div class="form-group has-success has-feedback">
                    ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </c:if>
        <div class="form-group has-success has-feedback"> <%-- value="${param.loginacct}" --%>
            <input type="text" class="form-control" id="loginacct" name="loginacct" value="superadmin" placeholder="请输入登录账号" autofocus> <!-- name="loginacct" 对应doLogin中的参数-->
            <%--    value="${param.loginacct}" 用于回显 用户名不丢 --%>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="userpswd" name="userpswd" value="123456" placeholder="请输入登录密码" style="margin-top:10px;">  <!-- name="userpswd" 对应doLogin中的参数 -->
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
<%--        <div class="form-group has-success has-feedback">--%>
<%--            <select class="form-control" >--%>
<%--                <option value="member">会员</option>--%>
<%--                <option value="user">管理</option>--%>
<%--            </select>--%>
<%--        </div>--%>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
    </form>
</div>
<%@include file="/WEB-INF/jsp/common/js.jsp"%> <!-- 静态包含: 生成一个class  适合不总变的情况-->
<script>
    function dologin() {
        // 用id选择器拿到登录的form id
        $("#loginForm").submit(); // 走到 action="doLogin"
    }
</script>
</body>
</html>