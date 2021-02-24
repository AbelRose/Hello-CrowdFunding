<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<html lang="zh-CN">
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
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表 </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i>  查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
<%--                            同步请求--%>
<%--                                数据正在加载中...--%>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li class="disabled"><a href="#">上一页</a></li>
                                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">下一页</a></li>
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
        // 页面加载完事件的处理
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
        initData(1);  // 将第一页的数据进行初始化
    });

    var json = {
        pageNum:1,
        pageSize:2
    };

    function initData(pageNum) {
        //1. 发起ajax请求 获取分页数据

        json.pageNum = pageNum;

        var index = -1;

        $.ajax(
            {
                type:'post',
                url:"${PATH}/role/loadData",
                data:json,
                beforeSend:function () {
                    // 进行表单数据校验
                    index = layer.load(0, {time:10*1000});
                    return true;
                },
                success:function(result) {
                    console.log(result); // result就是PageInfo
                    layer.close(index);

                    initShow(result);  // 声明到函数外部
                    initNavg(result);  // 声明到函数外部
                }
            }
        );
    }


    //2. 展示数据
    function initShow(result) {
        console.log(result);

        $('tbody').empty();

        var list = result.list;

        $.each(list,function(i,e){
            var tr = $('<tr></tr>');

            tr.append('<td>'+(i+1)+'</td>');
            tr.append('<td><input type="checkbox"></td>');
            tr.append('<td>'+e.name+'</td>');

            var td = $('<td></td>');
            td.append('<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>');
            td.append('<button type="button" roleId="'+e.id+'" class="updateClass btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>');
            td.append('<button type="button" roleId="'+e.id+'" class="deleteClass btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>');

            tr.append(td);

            tr.appendTo($('tbody'));
        });
    }

    //3. 展示分页条
    function initNavg(result) {
        console.log(result);

        $('.pagination').empty();

        var navigatepageNums = result.navigatepageNums;

        if(result.isFirstPage){
            $('.pagination').append($('<li class="disabled"><a href="#">上一页</a></li>'));
        }else{
            $('.pagination').append($('<li><a onclick="initData('+(result.pageNum-1)+')">上一页</a></li>'));
        }


        $.each(navigatepageNums,function(i,num){

            if(num == result.pageNum){
                $('.pagination').append($('<li class="active"><a href="#">'+num+' <span class="sr-only">(current)</span></a></li>'));
            }else{
                $('.pagination').append($('<li><a onclick="initData('+num+')">'+num+'</a></li>'));
            }

        });

        if(result.isLastPage){
            $('.pagination').append($('<li class="disabled"><a href="#">下一页</a></li>'));
        }else{
            $('.pagination').append($('<li><a onclick="initData('+(result.pageNum+1)+')">下一页</a></li>'));
        }
    }

</script>
</body>
</html>

