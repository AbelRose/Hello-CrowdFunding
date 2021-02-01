<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="utf-8" errorPage="/WEB-INF/jsp/error/error.jsp"
%>
<html>
<h2>Hello World!</h2>
<body>

    <%-- 1 --%><jsp:forward page="/index"></jsp:forward>

    <%-- 2  <jsp:forward page="index"></jsp:forward>--%>

<%--    http://localhost:8080/crowdfunding_main_war/crowdfunding_main_war/index.jsp--%>
    <%-- 3 --%><jsp:forward page="${pageContext.request.contextPath}"></jsp:forward>


    <%--
        绝对路径: 固定不能改变的路径
        C:\tmp\1.jsp
        http://127.0.0.1:8080/xxx/1.jsp
        <jsp:forward page="/index"></jsp:forward> 默认的地址和端口号以及上下文路径 http://localhost:8080/crowdfunding_main_war/index.jsp   crowdfunding_main_war是上下文路径

        相对路径: 与当前所访问的路径相关 是可变的路径
        ./1.jsp
        ../1.jsp
        ../../1.jsp
        <a href = "./1.jsp">
        <jsp:forward page="index"></jsp:forward> 和外面的index.jsp路径是一起的
        http://localhost:8080/crowdfunding_main_war/weclome.jsp
        http://localhost:8080/crowdfunding_main_war/index

        http://localhost:8080/crowdfunding_main_war/xxx/weclome.jsp
        http://localhost:8080/crowdfunding_main_war/xxx/index 相对于welcome.jsp的请求路径进行资源查找

        前台路径: 浏览器发起的请求路径.
        <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
        以斜杠开头表示 表示相对路径
        不以斜杠开头表示从服务器的根(ROOT)进行资源查找

        后台路径:
        <jsp:forward page="/index"></jsp:forward>

    --%>
</body>
</html>
