<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="/lib/html5shiv.js"></script>
    <script type="text/javascript" src="/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="/lib/layui/css/layui.css">
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>系统日志</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
    <span class="c-gray en">&gt;</span>
    系统管理
    <span class="c-gray en">&gt;</span>
    系统日志
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
    <div class="text-c"> 日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
        <input type="text" name="" id="" placeholder="日志名称" style="width:250px" class="input-text">
        <button type="button" class="btn btn-success radius" id="search" name="search"><i
                class="Hui-iconfont">&#xe665;</i> 搜日志
        </button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius" lay-event="getCheckData"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		</span>
        <span class="r">共有数据：<strong>54</strong> 条</span>
    </div>
    <table  class="table table-border table-bordered table-bg table-hover table-sort" lay-filter="test" style="margin-top: 10px">
        <%--class="table table-border table-bordered table-bg table-hover table-sort"--%>
        <%--<thead>--%>
        <%--<tr class="text-c">--%>
        <%--<th width="25"><input type="checkbox" name="" value=""></th>--%>
        <%--<th width="80">ID</th>--%>
        <%--<th width="100">类型</th>--%>
        <%--<th>内容</th>--%>
        <%--<th width="17%">用户名</th>--%>
        <%--<th width="120">客户端IP</th>--%>
        <%--<th width="120">时间</th>--%>
        <%--<th width="70">操作</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<tr class="text-c">--%>
        <%--<td><input type="checkbox" value="" name=""></td>--%>
        <%--<td>15686</td>--%>
        <%--<td>1</td>--%>
        <%--<td>登录成功!</td>--%>
        <%--<td>admin</td>--%>
        <%--<td>61.233.7.80</td>--%>
        <%--<td>2014-6-11 11:11:42</td>--%>
        <%--<td><a title="详情" href="javascript:;" onclick="system_log_show(this,'10001')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe665;</i></a>--%>
        <%--<a title="删除" href="javascript:;" onclick="system_log_del(this,'10001')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>--%>
        <%--</tr>--%>
        <%--</tbody>--%>
    </table>
    <script id="demo" type="text/html">
        <form class="layui-form" action="">
            <input type="hidden" name="id" value="{{ d.id || '' }}" autocomplete="off">

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>


                <div class="layui-input-inline">
                    <input type="text" name="username" value="{{ d.username || '' }}" lay-verify="required"
                           placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="mobile" value="{{ d.mobile || '' }}" lay-verify="phone" placeholder="请输入号码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" value="{{ d.email || '' }}" lay-verify="email" placeholder="请输入邮箱"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">选择性别</label>
                <div class="layui-input-block">
                    <%--            <script type="text/html" template>--%>

                    <input type="radio" name="sex" value="0" title="男"
                           {{# if(d.sex===0){ }}
                           checked
                           {{# } }}
                    />

                    <input type="radio" name="sex" value="1" title="女"
                           {{# if(d.sex===1){ }}
                           checked
                           {{# } }}
                    />

                    <input type="radio" name="sex"  value="2" title="保密"
                           {{# if(d.sex===2){ }}
                           checked
                           {{# } }}
                    />
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">留言内容</label>
                    <div class="layui-input-inline">
                <textarea type="text" name="remark"
                          class="layui-input">{{ d.remark || '' }}</textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-submit lay-filter="update_form_submit">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>

    </script>
    <div id="pageNav" class="pageNav"></div>
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
<script type="text/javascript"src="/lib/layui/layui.all.js"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>


<%--<script>--%>
<%--layui.use('table', function(){--%>
<%--var table = layui.table;--%>

<%--//第一个实例--%>
<%--table.render({--%>
<%--elem: '#demo'--%>
<%--,height: 312--%>
<%--,url: '/demo/table/user/' //数据接口--%>
<%--,page: true //开启分页--%>
<%--,cols: [[ //表头--%>
<%--{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}--%>
<%--,{field: 'username', title: '用户名', width:80}--%>
<%--,{field: 'sex', title: '性别', width:80, sort: true}--%>
<%--,{field: 'city', title: '城市', width:80}--%>
<%--,{field: 'sign', title: '签名', width: 177}--%>
<%--,{field: 'experience', title: '积分', width: 80, sort: true}--%>
<%--,{field: 'score', title: '评分', width: 80, sort: true}--%>
<%--,{field: 'classify', title: '职业', width: 80}--%>
<%--,{field: 'wealth', title: '财富', width: 135, sort: true}--%>
<%--]]--%>
<%--});--%>

<%--});--%>
<%--</script>--%>
<%--<script>--%>
<%--// layui.use('table', function(){--%>
<%--//     var table = layui.table;--%>
<%--//--%>
<%--//     //第一个实例--%>
<%--//     table.render({--%>
<%--//         elem: '#demo'--%>
<%--//         ,height: 312--%>
<%--//         ,url: '/log/all' //数据接口--%>
<%--//         ,page: true //开启分页--%>
<%--//         ,cols: [[ //表头--%>
<%--//             {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}--%>
<%--//             ,{field: 'username', title: '用户名', width:80}--%>
<%--//             ,{field: 'sex', title: '性别', width:80, sort: true}--%>
<%--//             ,{field: 'city', title: '城市', width:80}--%>
<%--//             ,{field: 'sign', title: '签名', width: 177}--%>
<%--//             ,{field: 'experience', title: '积分', width: 80, sort: true}--%>
<%--//             ,{field: 'score', title: '评分', width: 80, sort: true}--%>
<%--//             ,{field: 'classify', title: '职业', width: 80}--%>
<%--//             ,{field: 'wealth', title: '财富', width: 135, sort: true}--%>
<%--//         ]]--%>
<%--//     });--%>
<%--//--%>
<%--// });--%>
<%--$.ajax({--%>
<%--type: 'POST',--%>
<%--url: '/log/all',--%>
<%--dataType: 'json',--%>
<%--success: function(data){--%>
<%--//var total=document.getElementsByClassName("total")[0];--%>
<%--var  total=document.get--%>
<%--total.innerHTML=data.data.length;--%>
<%--//window.location.reload();--%>
<%--console.log(data);--%>
<%--for (var i = 0; i <data.data.length ; i++) {--%>
<%--var d=data.data[i];--%>
<%--if(d.gender==1){--%>
<%--var sex='男';--%>
<%--}else if(d.gender==2){--%>
<%--var sex='女';--%>
<%--}else{--%>
<%--var sex='保密';--%>
<%--}--%>
<%--var li='<tr class="text-c">\n' +--%>
<%--'<td><input type="checkbox" value="1" name=""></td>\n' +--%>
<%--'<td>'+d.id+'</td>\n' +--%>
<%--'<td><u style="cursor:pointer" class="text-primary" onclick="member_show(\'张三\',\'/member/show\',\'10001\',\'360\',\'400\')">'+d.username+'</u></td>\n' +--%>
<%--'<td>'+sex+'</td>\n' +--%>
<%--'<td>'+d.telephone+'</td>\n' +--%>
<%--'<td>'+d.email+'</td>\n' +--%>
<%--'<td class="text-l">'+d.address+'</td>\n' +--%>
<%--'<td>'+d.createTime+'</td>\n' +--%>
<%--'<td class="td-status"><span class="label label-success radius">已启用</span></td>\n' +--%>
<%--'<td class="td-manage"><a style="text-decoration:none" onClick="member_stop(this,\'10001\')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="member_edit(\'编辑\',\'member-add.html\',\'4\',\'\',\'510\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password(\'修改密码\',\'change-password.html\',\'10001\',\'600\',\'270\')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>\n' +--%>
<%--'</tr>';--%>
<%--$("#jilu").append(li);--%>
<%--}--%>
<%--},--%>
<%--error:function(data) {--%>
<%--console.log(data);--%>
<%--}--%>
<%--});--%>

<%--</script>--%>
<%--<script type="text/javascript">--%>
<%--loadData();--%>
<%--function loadData() {--%>
<%--$.ajax({--%>
<%--type: "post",--%>
<%--async: false,--%>
<%--url: "/log/all",--%>
<%--dataType: "json",--%>
<%--success: function success(result) {--%>
<%--console.log(result.data);--%>
<%--}--%>
<%--});--%>
<%--}--%>

<%--$('.table-sort').dataTable({--%>
<%--"lengthMenu":false,//显示数量选择--%>
<%--"bFilter": false,//过滤功能--%>
<%--"bPaginate": false,//翻页信息--%>
<%--"bInfo": false,//数量信息--%>
<%--"aaSorting": [[ 1, "desc" ]],//默认第几个排序--%>
<%--"bStateSave": true,//状态保存--%>
<%--"aoColumnDefs": [--%>
<%--//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示--%>
<%--{"orderable":false,"aTargets":[0,7]}// 制定列不参与排序--%>
<%--]--%>
<%--});--%>

<%--/*查看日志*/--%>
<%--function system_log_show(){--%>

<%--}--%>
<%--/*日志-删除*/--%>
<%--function system_log_del(obj,id){--%>
<%--layer.confirm('确认要删除吗？',function(index){--%>
<%--$.ajax({--%>
<%--type: 'POST',--%>
<%--url: '',--%>
<%--dataType: 'json',--%>
<%--success: function(data){--%>
<%--$(obj).parents("tr").remove();--%>
<%--layer.msg('已删除!',{icon:1,time:1000});--%>
<%--},--%>
<%--error:function(data) {--%>
<%--console.log(data.msg);--%>
<%--},--%>
<%--});--%>
<%--});--%>
<%--}--%>
<%--var table = layui.table;--%>
<%--table.render({--%>
<%--elem: '#demo'--%>
<%--,height: 312--%>
<%--,url: '/log/all' //数据接口--%>
<%--,page: true //开启分页--%>
<%--,cols: [[ //表头--%>

<%--{field: 'id', title: 'id', width:80}--%>
<%--,{field: 'succeed', title: '类型', width:80, sort: true}--%>
<%--,{field: 'action', title: '内容', width:80}--%>
<%--,{field: 'logDescription', title: '用户名', width: 177}--%>
<%--,{field: 'ip', title: 'ip', width: 80, sort: true}--%>
<%--,{field: 'createtime', title: '时间', width: 80, sort: true}--%>

<%--]]--%>
<%--});--%>
<%--</script>--%>
<%--<script>--%>
<%--layui.use('table', function(){--%>
<%--var table = layui.table;--%>

<%--//第一个实例--%>


<%--});--%>
<%--</script>--%>
<%--<script type="text/javascript">--%>
<%--$(function () {--%>
<%--layui.use(['table', 'laytpl', 'element'], function () {--%>
<%--var table = layui.table;--%>
<%--var laytpl = layui.laytpl;--%>
<%--var element = layui.element;--%>
<%--$("#search").click(function () {--%>
<%--reload();--%>
<%--})--%>

<%--function reload() {--%>
<%--table.reload('table', {--%>
<%--where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）--%>
<%--start: $("#start").val(),--%>
<%--end: $("#end").val(),--%>
<%--keywords: $("#keywords").val()--%>
<%--}--%>
<%--});--%>
<%--}--%>

<%--$(function () {--%>
<%--table.render({--%>
<%--id: 'table',--%>
<%--elem: '.table-sort'--%>
<%--, toolbar: '#toolbarDemo'--%>
<%--, height: 'full-220'--%>
<%--, url: '/log/all' //数据接口--%>
<%--, page: true //开启分页--%>
<%--, cols: [[ //表头--%>
<%--{type: 'checkbox', fixed: 'left'}--%>
<%--, {--%>
<%--field: 'id', title: 'id', width: '15%', sort: true, fixed: 'left', templet: function (d) {--%>
<%--return d.id;//long 转Stirng--%>
<%--}--%>
<%--}--%>
<%--,--%>
<%--{field: 'succeed', title: '类型', width: '10%'}--%>
<%--, {field: 'action', title: '内容', width: '10%'}--%>
<%--, {field: 'logDescription', title: '用户名', width: '10%'}--%>
<%--, {--%>
<%--field: 'ip', title: 'ip', width: '10%'--%>

<%--}--%>
<%--, {field: 'createtime', title: '时间', width: '30%'}--%>


<%--]]--%>

<%--});--%>
<%--table.on('toolbar(test)', function (obj) {--%>
<%--var checkStatus = table.checkStatus(obj.config.id);--%>
<%--switch (obj.event) {--%>
<%--case 'getCheckData':--%>
<%--var data = checkStatus.data;--%>
<%--if (data == null || data.length === 0) {--%>
<%--layer.msg("请先选择需要删除的数据");--%>
<%--} else {--%>
<%--var arr = [];--%>
<%--for (var i = 0; i < data.length; i++) {--%>
<%--arr.push(data[i].id);--%>
<%--}--%>
<%--layer.alert(arr.join(','));--%>
<%--layer.confirm('真的删除这些数据么', function (index) {--%>
<%--$.ajax({--%>
<%--"url": "/suggestings/delete",--%>
<%--"data": {--%>
<%--ids: arr.join(',')--%>
<%--},--%>
<%--type: "get",--%>
<%--dataType: "json",--%>
<%--success: function (data) {--%>
<%--if (data.code === 200) {--%>
<%--layer.msg("删除成功");--%>
<%--reload();--%>

<%--layer.close(index);--%>
<%--} else {--%>
<%--layer.msg(data.msg);--%>
<%--}--%>
<%--}--%>
<%--})--%>

<%--});--%>
<%--}--%>

<%--break;--%>

<%--}--%>
<%--;--%>
<%--});--%>
<%--table.on('tool(test)', function (obj) {--%>
<%--var data = obj.data;--%>

<%--console.log(data);--%>
<%--if (obj.event === 'del') {--%>
<%--layer.confirm('真的删除行么', function (index) {--%>
<%--$.ajax({--%>
<%--"url": "/suggestings/delete",--%>
<%--"data": {--%>
<%--ids: data.id--%>
<%--},--%>
<%--type: "get",--%>
<%--dataType: "json",--%>
<%--success: function (res) {--%>
<%--if (res.code === 200) {--%>
<%--layer.msg("删除成功");--%>
<%--obj.del();--%>
<%--layer.close(index);--%>
<%--} else {--%>
<%--layer.msg(res.msg);--%>
<%--}--%>
<%--}--%>
<%--})--%>
<%--});--%>
<%--} else if (obj.event === 'edit') {--%>
<%--var getTpl = $("#demo")[0].innerHTML;--%>
<%--laytpl(getTpl).render(data, function (html) {--%>
<%--var index = layer.open({--%>
<%--type: 1,--%>
<%--content: html,--%>
<%--area: ['500px', '600px']--%>
<%--});--%>
<%--element.render();--%>
<%--});--%>
<%--}--%>
<%--});--%>
<%--});--%>
<%--});--%>
<%--}--%>
<%--</script>--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">批量删除</button>
        <%--<button class="layui-btn layui-btn-sm" lay-event="add">添加</button>--%>

    </div>
</script>
<script type="text/javascript">
    $(function () {
        layui.use(['table', 'laytpl', 'element','form'], function () {
            var table = layui.table;
            var laytpl = layui.laytpl;
            var element = layui.element;
            var form=layui.form;
            $("#search").click(function () {
                reload();
            })
            function reload() {
                table.reload('table', {
                    where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                        start: $("#start").val(),
                        end: $("#end").val(),
                        keywords: $("#keywords").val()
                    }
                });
            }


            //第一个实例
            table.render({
                id: 'table',
                elem: '.table-sort'
                , toolbar: '#toolbarDemo'
                , height: 'full-220'
                , url: '/log/all' //数据接口
                , page: true //开启分页
                , cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: 'ID', width: '15%', sort: true, fixed: 'left', templet: function (d) {
                            return d.id;//long 转Stirng
                        }
                    }, {field: 'succeed', title: '类型', width: '10%'}
                    , {field: 'action', title: '内容', width: '10%'}
                    , {field: 'classname', title: '用户名', width: '10%'}
                    , {field: 'ip', title: 'ip', width: '10%'}
                    , {field: 'createtime', title: '时间', width: '30%'}
                    , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: '10%'}
                ]]
            });
            function addOrUpdate(data) {
                var getTpl = document.getElementById("demo").innerHTML;
                laytpl(getTpl).render(data, function (html) {
                    var index = layer.open({
                        type: 1,
                        content: html,
                        area: ['500px', '600px']
                    });
                    form.render();
                    form.on('submit(update_form_submit)', function (data) {
                        layer.msg(JSON.stringify(data.field));
                        $.ajax({
                            "url": "/log/updateOrAdd",
                            "data": JSON.stringify(data.field),
                            type: "post",
                            contentType: 'application/json',
                            dataType: "json",
                            success: function (res) {
                                if (res.code === 200) {
                                    layer.msg("操作成功");
                                    reload();
                                    layer.close(index);
                                } else {
                                    layer.msg(res.msg);
                                }
                            }
                        })
                        return false;
                    });
                });
            }
            //头工具栏事件
            table.on('toolbar(test)', function (obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {

                    case 'getCheckData':
                        var data = checkStatus.data;
                        if (data == null || data.length === 0) {
                            layer.msg("请先选择需要删除的数据");
                        } else {
                            var arr = [];
                            for (var i = 0; i < data.length; i++) {
                                arr.push(data[i].id);
                            }
                            layer.alert(arr.join(','));
                            layer.confirm('真的删除这些数据么', function (index) {
                                $.ajax({
                                    "url": "/log/delete",
                                    "data": {
                                        ids: arr.join(',')
                                    },
                                    type: "get",
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code === 200) {
                                            layer.msg("删除成功");
                                            reload();

                                            layer.close(index);
                                        } else {
                                            layer.msg(data.msg);
                                        }
                                    }
                                })

                            });
                        }

                        break;

                }
                ;
            });

            //监听行工具事件
            table.on('tool(test)', function (obj) {
                var data = obj.data;

                console.log(data);
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        $.ajax({
                            "url": "/log/delete",
                            "data": {
                                ids: data.id
                            },
                            type: "get",
                            dataType: "json",
                            success: function (res) {
                                if (res.code === 200) {
                                    layer.msg("删除成功");
                                    obj.del();
                                    layer.close(index);
                                } else {
                                    layer.msg(res.msg);
                                }
                            }
                        })
                    });
                } else if (obj.event === 'edit') {
                    addOrUpdate(data);

                }
            });
        });

    });

    /*用户-添加*/
    function member_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*用户-查看*/
    function member_show(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
            $(obj).remove();
            layer.msg('已停用!', {icon: 5, time: 1000});
        });
    }

    /*用户-启用*/
    function member_start(obj, id) {
        layer.confirm('确认要启用吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
            $(obj).remove();
            layer.msg('已启用!', {icon: 6, time: 1000});
        });
    }

    /*用户-编辑*/
    function member_edit(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }

    /*密码-修改*/
    function change_password(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function (data) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });
        });

    }

</script>
</body>
</html>