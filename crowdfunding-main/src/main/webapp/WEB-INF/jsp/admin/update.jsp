<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@include file="/WEB-INF/jsp/common/css.jsp" %> <!-- 静态包含: 生成一个class  适合不总变的情况-->

    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<%@include file="/WEB-INF/jsp/common/top.jsp" %> <!-- 静态包含: 生成一个class  适合不总变的情况-->


<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/jsp/common/sidebar.jsp" %> <!-- 静态包含: 生成一个class  适合不总变的情况-->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form id="updateForm" role="form" action="${PATH}/admin/doUpdate" method="post">
<%--                        action 是前台的路径--%>
                        <div class="form-group">
                            <label for="loginacct">登陆账号</label>
<%--                        需要新加一个隐含域 为了拿到admin的ID--%>
                            <input type="hidden" name="id" value="${admin.id}">

                            <input type="hidden" name="pageNum" value="${param.pageNum}">
                            <input type="text" class="form-control" id="loginacct" name="loginacct" value="${admin.loginacct}" placeholder="请输入登陆账号">
<%--                        为了能够回显 在请求域之中是可以拿到的 value="${admin.loginacct}"--%>
                        </div>
                        <div class="form-group">
                            <label for="username">用户名称</label>
                            <input type="text" class="form-control" id="username" name="username" value="${admin.username}" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" name="email" value="${admin.email}" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button id="updateBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改</button>
                        <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/js.jsp" %> <!-- 静态包含: 生成一个class  适合不总变的情况-->
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

        $("#updateBtn").click(function () {
            $("#updateForm").submit();
        })
    });
</script>
</body>
</html>
