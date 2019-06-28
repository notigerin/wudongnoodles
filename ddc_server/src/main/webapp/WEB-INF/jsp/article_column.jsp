<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="/lib/html5shiv.js"></script>
    <script type="text/javascript" src="/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>栏目管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
    <span class="c-gray en">&gt;</span>
    资讯管理
    <span class="c-gray en">&gt;</span>
    栏目管理
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
       href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
    <div class="text-c">日期范围


            <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'end\')||\'%y-%M-%d\'}' })" id="start"
                   class="input-text Wdate" style="width:120px;" name="start"/>
            <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'start\')}',maxDate:'%y-%M-%d' })" id="end"
                   class="input-text Wdate" style="width:120px;" name="end"/>
        <input type="text" name="keyword" placeholder="栏目名称" style="width:250px" class="input-text">
        <button name="" id="btn" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		<a class="btn btn-primary radius" onclick="system_category_add('添加栏目','article_column-add')"
           href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加栏目</a>
		</span>
        <span class="r">共有数据：<strong class="total">54</strong> 条</span>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="80">ID</th>
                <th width="80">排序</th>
                <th>栏目名称</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody id="table">
            <%--            <tr class="text-c">--%>
            <%--                <td><input type="checkbox" name="" value=""></td>--%>
            <%--                <td>3</td>--%>
            <%--                <td>3</td>--%>
            <%--                <td class="text-l">&nbsp;&nbsp;├&nbsp;二级栏目</td>--%>
            <%--                <td class="f-14"><a title="编辑" href="javascript:;"--%>
            <%--                                    onclick="system_category_edit('栏目编辑','article_column-add.html','3','700','480')"--%>
            <%--                                    style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>--%>
            <%--                    <a title="删除" href="javascript:;" onclick="system_category_del(this,'3')" class="ml-5"--%>
            <%--                       style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>--%>
            <%--            </tr>--%>
            </tbody>
        </table>
    </div>


</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script>
    //请求数据
    (function () {
        $.ajax({
            type: 'post',
            url: '/column/list',
            dataType: 'json',
            success: function (data) {
                var total = document.getElementsByClassName("total")[0];
                //console.log(total);
                total.innerText = data.data.length;
                var sort = 1;
                for (var i = 0; i < data.data.length; i++) {
                    var d = data.data[i];
                    if (d.columnDeleteFlag == 0) {

                        var li = "<tr class=\"text-c\">\n" +
                            "\t\t\t\t\t<td><input type=\"checkbox\" name=\"batches\" value=" + d.columnId + "></td>\n" +
                            "\t\t\t\t\t<td>" + d.columnId + "</td>\n" +
                            "\t\t\t\t\t<td>" + sort + "</td>\n" +
                            "\t\t\t\t\t<td class=\"text-l\">&nbsp;&nbsp;├&nbsp;" + d.columnName + "</td>\n" +
                            "\t\t\t\t\t<td class=\"f-14\"><a title=\"编辑\" href=\"javascript:;\" onclick=\"system_category_edit('栏目编辑','/column/article_column_edit?columnId="+ d.columnId +"','3','700','480')\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>\n" +
                            "\t\t\t\t\t\t<a class='delete_column' value="+d.columnId+" title=\"删除\" href=\"javascript:;\" onclick='system_category_del(this)' class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>\n" +
                            "\t\t\t\t</tr>";
                        sort++;
                        $("#table").append(li);
                    }
                }
            },
            error: function (data) {
                console.log(data + "请求失败！");
            }
        });
    })();

</script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    /*系统-栏目-添加*/
    function system_category_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*系统-栏目-编辑*/
    function system_category_edit(title, url, id, w, h) {

        layer_show(title, url, w, h);
    }

    /*系统-栏目-删除*/
    function system_category_del(obj) {
        var value=obj.parentNode.parentNode.children[1].innerHTML;
        console.log(value);
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '/column/delete',
                data:{id:value},
                success: function (data) {
                    console.log(data);
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data);
                },
            });
        });
    }

    setTimeout(function () {
        $('.table-sort').dataTable({
            "aaSorting": [[1, "desc"]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                {"orderable": false, "aTargets": [0, 4]}// 制定列不参与排序
            ]
        });
    }, 200);

    var btn = document.getElementById("btn");
    btn.onclick = function search() {
        var value = document.getElementsByName("keyword")[0].value;
        $.ajax({
            type: 'POST',
            url: '/column/search',
            data: {key: value},
            dataType: 'json',
            success: function (data) {
                console.log(data);
                $("#table").empty();
                var total = document.getElementsByClassName("total")[0];
                //console.log(total);
                total.innerText = data.data.length;
                var sort = 1;
                for (var i = 0; i < data.data.length; i++) {
                    var d = data.data[i];
                    if (d.columnDeleteFlag == 0) {
                        var li = "<tr class=\"text-c\">\n" +
                            "\t\t\t\t\t<td><input type=\"checkbox\" name=\"batches\" value=" + d.columnId + "></td>\n" +
                            "\t\t\t\t\t<td>" + d.columnId + "</td>\n" +
                            "\t\t\t\t\t<td>" + sort + "</td>\n" +
                            "\t\t\t\t\t<td class=\"text-l\">&nbsp;&nbsp;├&nbsp;" + d.columnName + "</td>\n" +
                            "\t\t\t\t\t<td class=\"f-14\"><a title=\"编辑\" href=\"javascript:;\" onclick=\"system_category_edit('栏目编辑','/column/article_column_edit?columnId="+ d.columnId +"','3','700','480')\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>\n" +
                            "\t\t\t\t\t\t<a title=\"删除\" href=\"/column/delete?columnID=" + d.columnId + "\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>\n" +
                            "\t\t\t\t</tr>";
                        sort++;
                        $("#table").append(li);
                    }
                }
            },
            error: function (data) {
                console.log(data);
            },
        });

    }

</script>
</body>
</html>