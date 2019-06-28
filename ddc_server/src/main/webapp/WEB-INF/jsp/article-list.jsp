<%--
  Created by IntelliJ IDEA.
  User: 164328
  Date: 2019/6/18
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <title>资讯列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span
        class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <!--<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>-->
        <span class="select-box inline">
		<select name="" class="select">
			<option value="0">全部分类</option>
			<option value="1">分类一</option>
			<option value="2">分类二</option>
		</select>
		</span> 日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin"
               class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax"
               class="input-text Wdate" style="width:120px;">
        <input type="text" name=""  placeholder=" 资讯名称" style="width:250px" class="input-text">
        <button name="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜资讯</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:;" onclick="batchDeletes()"
                                                               class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a
            class="btn btn-primary radius" data-title="添加资讯" data-href="/page/article-add" onclick="Hui_admin_tab(this)"
            href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a></span>
<%--        <span--%>
<%--            class="r">共有数据：<strong>12</strong> 条</span>--%>
    </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="80">ID</th>
                <th>标题</th>
                <th width="80">分类</th>
                <th width="80">来源</th>
                <th width="120">更新时间</th>
                <th width="75">浏览次数</th>
                <th width="60">发布状态</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
<%--            <c:forEach items="${articleList}" var="article">--%>
<%--                <tr class="text-c">--%>
<%--                    <td><input type="checkbox" value="" name=""></td>--%>
<%--                    <td>${article.id}</td>--%>
<%--                    <td class="text-l"><u style="cursor:pointer" class="text-primary"--%>
<%--                                          onClick="article_edit('查看','#','${article.id}')" title="查看">${article.title}</u></td>--%>
<%--                    <td>${article.classify}</td>--%>
<%--                    <td>${article.createBy}</td>--%>
<%--                    <td>${article.updateTime}</td>--%>
<%--                    <td>${article.frequency}</td>--%>
<%--                    <td class="td-status"><span class="label label-success radius">${article.stateFlag}</span></td>--%>
<%--                    <td class="f-14 td-manage"><a style="text-decoration:none" onClick="article_stop(this,'${article.id}')"--%>
<%--                                                  href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> <a--%>
<%--                            style="text-decoration:none" class="ml-5"--%>
<%--                            onClick="article_edit('资讯编辑','/article/add','${article.id}')" href="javascript:;" title="编辑"><i--%>
<%--                            class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5"--%>
<%--                                                                     onClick="article_del(this,'${article.id}')" href="javascript:;"--%>
<%--                                                                     title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

    setTimeout(function () {
        $('.table-sort').dataTable({
            "aaSorting": [[1, "desc"]],//默认第几个排序
            "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "所有"]],
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                {"orderable": false, "aTargets": [0, 4]}// 制定列不参与排序
            ]
        });
    }, 200);

    function  list() {
        $.ajax({
            type: 'post',
            url: '/article/list',
            dataType: 'json',
            success: function (data) {
                //alert(data.data.length);
                for (var i = 0; i < data.data.length; i++) {
                    var d = data.data[i];
                    //$("#demo01").attr("sum",d);
                    if (d.stateFalg == 1) {
                        var status = "已发布";
                        var unstatus = "下架";
                        var staclass = "label label-success radius";
                    } else {
                        var status = "已下架";
                        var unstatus = "发布";
                        var staclass = "label label-defaunt radius"
                    }
                    var li = "<tr class=\"text-c\">" +
                        "<td><input type=\"checkbox\" value=\"" + d.id + "\" name=\"subcheck\"></td>" +
                        "<td>" + d.id + "</td>" +
                        "<td>" + d.title + "</td>" +
                        "<td>" + d.classify + "</td>" +
                        "<td>" + d.resource + "</td>" +
                        "<td>" + d.updateTime+ "</td>" +
                        "<td>" + d.frequency + "</td>" +
                        "<td class=\"td-status\"><span class=\"" + staclass + "\">" + status + "</span></td>" +
                        "<td class=\"f-14 td-manage\"><a style=\"text-decoration:none\" onClick=\"article_stop(this,"+ d.id +")\" href=\"javascript:;\" title=\"" + unstatus + "\"><i class=\"Hui-iconfont\">&#xe603;</i></a> " +
                        "<a title=\"编辑\" href=\"javascript:;\" onclick=\"article_edit('管理员编辑','/article/upDisplay',"+ d.id +",'800','500')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> " +
                        "<a title=\"删除\" href=\"javascript:;\" onclick=\"article_del(this,"+d.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>" +
                        "</td>" +
                        "</tr>";
                    $("#tbody").append(li);
                }
            },
            error: function (data) {
                console.log(data + "111");
            }
        });
    }
    list();

    //批量删除
    function batchDeletes() {
        var checkedNum = $("input[name='subcheck']:checked").length;
        if (checkedNum == 0) {
            alert("请至少选择一项!");
            return false;
        }
        if (confirm("确定删除所选项目?")) {
            var checkedList = new Array();
            $("input[name='subcheck']:checked").each(function () {
                checkedList.push($(this).val());
            });
            $.ajax({
                type: "POST",
                url: "/article/batchDel",
                data: {"delItems": checkedList.toString()},
                datatype: "json",
                success: function (data) {
                    $("[name='checkbox2']:checkbox").attr("checked", false);
                },
                error: function (data) {
                    art.dialog.tips('删除失败!');
                }
            });
            window.location.reload()
    }
    }


        // $(function() {
    //     setTimeout($('.table-sort').dataTable({
    //         "aaSorting": [[1, "desc"]],//默认第几个排序
    //         "bStateSave": true,//状态保存
    //         "pading": false,
    //         "aoColumnDefs": [
    //             //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
    //             {"orderable": false, "aTargets": [0, 8]}// 不参与排序的列
    //         ]
    //     }), 1);
    // }

    /*资讯-添加*/
    function article_add(title, url, w, h) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*资讯-编辑*/
    function article_edit(title, url, id, w, h) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url+"?id="+id
        });
        layer.full(index);
    }

    /*资讯-删除*/
    function article_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '/article/del?id='+id,
                dataType: 'json',
                success: function (data) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });
            window.location.reload()
        });
    }

    /*资讯-审核*/
    function article_shenhe(obj, id) {
        layer.confirm('审核文章？', {
                btn: ['通过', '不通过', '取消'],
                shade: false,
                closeBtn: 0
            },
            function () {
                $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
                $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                $(obj).remove();
                layer.msg('已发布', {icon: 6, time: 1000});
            },
            function () {
                $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
                $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
                $(obj).remove();
                layer.msg('未通过', {icon: 5, time: 1000});
            });
    }

    /*资讯-下架*/
    function article_stop(obj, id) {
        layer.confirm('确认要下架吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
            $(obj).remove();
            layer.msg('已下架!', {icon: 5, time: 1000});
        });
    }

    /*资讯-发布*/
    function article_start(obj, id) {
        layer.confirm('确认要发布吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
            $(obj).remove();
            layer.msg('已发布!', {icon: 6, time: 1000});
        });
    }

    /*资讯-申请上线*/
    function article_shenqing(obj, id) {
        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
        $(obj).parents("tr").find(".td-manage").html("");
        layer.msg('已提交申请，耐心等待审核!', {icon: 1, time: 2000});
    }

</script>
</body>
</html>
