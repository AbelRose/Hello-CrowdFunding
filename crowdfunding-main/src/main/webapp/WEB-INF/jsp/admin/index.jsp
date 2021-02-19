<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>

<%@include file="/WEB-INF/jsp/common/top.jsp" %> <!-- 静态包含: 生成一个class  适合不总变的情况-->

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/jsp/common/sidebar.jsp" %> <!-- 静态包含: 生成一个class  适合不总变的情况-->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form id="queryForm" class="form-inline" role="form" style="float:left;"action="${PATH}/admin/index" method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" name="condition" value="${param.condition}" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning" onclick="$('#queryForm').submit()"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button id="deleteBatchBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href= window.location.href='${PATH}/admin/toAdd'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="selectAll" type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="admin" varStatus="status">
                                <%--                            var是page.list赋值的变量   ${status.count}是迭代的序号 --%>
                            <tr>
                                <td>${status.count}</td>
                                <td><input type="checkbox" adminId="${admin.id}"></td>
                                <td>${admin.loginacct}</td>
                                <td>${admin.username}</td>
                                <td>${admin.email}</td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs"><i
                                            class=" glyphicon glyphicon-check"></i></button>
                                    <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href='${PATH}/admin/toUpdate?pageNum=${page.pageNum}&id=${admin.id}"><i
                                            class=" glyphicon glyphicon-pencil"></i></button>
                                        <%--                                    onclick 按钮的事件--%>
                                        <%--                                    <button type="button" class="btn btn-danger btn-xs" onclick="window.location.href='${PATH}/admin/doDelete?pageNum=${page.pageNum}&id=${admin.id}'"><i--%>
                                        <%--                                            class=" glyphicon glyphicon-remove"></i></button>--%>
                                        <%--使用弹层组件--%>
                                    <button type="button" adminId="${admin.id}" class="deleteBtnClass btn btn-danger btn-xs"><i
                                            class=" glyphicon glyphicon-remove"></i></button>
                                        <%--adminId="${admin.id}" 把值挂在自定义的属性上 触发事件的时候取自定义的属性的值就可以--%>
                                </td>
                            </tr>
                            </c:forEach>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">

                                        <c:if test="${page.isFirstPage }">
                                            <li class="disabled"><a href="#">上一页</a></li>
                                        </c:if>

                                        <c:if test="${!page.isFirstPage }">
                                            <li>
                                                <a href="${PATH}/admin/index?condition=${param.condition}&pageNum=${page.pageNum-1}">上一页</a>
                                            </li>
                                        </c:if>


                                        <c:forEach items="${page.navigatepageNums}" var="num">
                                            <c:if test="${num == page.pageNum }">
                                                <li class="active"><a
                                                        href="${PATH}/admin/index?condition=${param.condition}&pageNum=${num}">${num}
                                                    <span class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${num != page.pageNum }">
                                                <li>
                                                    <a href="${PATH}/admin/index?condition=${param.condition}&pageNum=${num}">${num}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>


                                        <c:if test="${page.isLastPage }">
                                            <li class="disabled"><a href="#">下一页</a></li>
                                        </c:if>

                                        <c:if test="${!page.isLastPage }">
                                            <li>
                                                <a href="${PATH}/admin/index?condition=${param.condition}&pageNum=${page.pageNum+1}">下一页</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/js.jsp" %> <!-- 静态包含: 生成一个class  适合不总变的情况-->
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });

    // 把所有的这样的按钮都拿到
    $(".deleteBtnClass").click(function () {

        var id = $(this).attr("adminId");
        //询问框
        layer.confirm('您是否确定删除该条数据？', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            window.location.href="window.location.href='${PATH}/admin/doDelete?pageNum=${page.pageNum}&id="+id;
            layer.close(index);
        }, function(index){
            layer.close(index);
        });
    });

    // 设置全选框
    $("#selectAll").click(function () {
        // $("tbody input[type='checkbox']").attr("checked", this.checked());  attr用来获取自定的属性 但是有bug
        $("tbody input[type='checkbox']").prop("checked", this.checked);
    });

    // 批量删除 deleteBatchBtn
    $("#deleteBatchBtn").click(function () {
        var checkedBoxList = $("tbody input[type='checkbox']:checked")

        if (checkedBoxList.length == 0) {
            layer.msg("请选中在删除吧!");
            return false;
        }

        // 传给后台
        // var ids = '1, 2, 3, 4, 5';
        <%--var ids = '';--%>
        <%--$.each(checkedBoxList, function (i, e) {--%>
        <%--    var adminId = ${e}.attr("adminId");  // 获取自定义属性--%>
        <%--    if (i != 0) {--%>
        <%--        ids += ',';--%>
        <%--    }--%>
        <%--    ids += e;--%>
        <%--})--%>


        var ids = '';
        var array = new Array();
        $.each(checkedBoxList, function (i, e) {
            var adminId = $(e).attr("adminId");  // 获取自定义属性
            array.push(adminId);
        });
        ids = array.join(",");
        //询问框
        layer.confirm('您是否确定删除这些数据？', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            // 传给后台
            window.location.href="${PATH}/admin/doDeleteBatch?pageNum=${page.pageNum}&ids="+ids;
            layer.close(index);
        }, function(index){
            layer.close(index);
        });
    });



</script>
</body>
</html>
