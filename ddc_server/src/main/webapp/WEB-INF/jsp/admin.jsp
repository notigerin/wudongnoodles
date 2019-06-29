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
    <%--	<link rel="stylesheet" type="text/css" href="/lib/layui/css/layui.css" />--%>
    <link rel="stylesheet" href="/lib/layui/css/layui.css" media="all">
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->

    <title>角色列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span
        class="c-gray en">&gt;</span> 角色列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c"> 日期范围
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'end\')||\'%y-%M-%d\'}' })" id="start"
               class="input-text Wdate" style="width:120px;" name="start"/>
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'start\')}',maxDate:'%y-%M-%d' })" id="end"
               class="input-text Wdate" style="width:120px;" name="end"/>
        <input type="text" class="input-text" style="width:250px" placeholder="输入关键词" id="keywords" name="keywords"/>
        <button type="button" class="btn btn-success radius" id="search" name="search"><i
                class="Hui-iconfont">&#xe665;</i> 搜索
        </button>
    </div>
    <table class="table table-border table-bordered table-hover table-bg table-sort" lay-filter="test"
           style="margin-top: 10px;">

    </table>
</div>


<script id="demo" type="text/html">
    <form class="layui-form" action="">
        <input type="hidden" name="id" value="{{ d.id || '' }}" autocomplete="off">

        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" value="{{ d.name || '' }}" lay-verify="required"
                       placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" value="{{ '' }}"
                       placeholder="请输入密码" autocomplete="off" class="layui-input">
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
            <label class="layui-form-label">角色</label>
            <div class="layui-input-inline">
                <select class="select" name="roleId" size="1" id="roleId">
                    <option value="" name="roleId" disabled>请为管理员分配角色</option>
                    {{# layui.each(d.data, function(index, item){ }}

                    <option value="{{item.id}}" name="roleId"
                            {{# if(item.id === d.roleId){ }}
                            selected
                            {{# } }}
                    >{{item.name}}</option>
                    {{# }); }}
                </select>
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

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <%--            <script type="text/html" template>--%>

                <input type="radio" name="status" value="1" title="启用"
                       {{# if(d.status===0){ }}
                       checked
                       {{# } }}
                />
                <input type="radio" name="status" value="2" title="停用"
                       {{# if(d.status===1){ }}
                       checked
                       {{# } }}
                />

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">留言内容</label>
            <div class="layui-input-inline">
                <textarea type="text" name="remark"
                          class="layui-input">{{ d.remark || '' }}</textarea>
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
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<%--<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>--%>
<%--<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->--%>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<%--<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>--%>
<script type="text/javascript" src="/lib/layui/layui.all.js"></script>
<%--<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">批量删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>

    </div>
</script>
<script type="text/html" id="barDemo">
    {{# if(d.status===0){ }}
    <a class="layui-btn layui-btn-xs layui-bg-gray" lay-event="stop" title="停用"><i class="layui-icon layui-icon-face-surprised" style="font-size: 30px; color: #3f324d;"> 停用 </i></a>
    {{# }else{ }}
    <a class="layui-btn layui-btn-xs layui-bg-red" lay-event="run" title="启用"><i class="layui-icon layui-icon-fire" style="font-size: 30px; color: #00a5ff;"> 启用 </i></a>
    {{# } }}
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
    $(function () {
        layui.use(['table', 'laytpl', 'element', 'form'], function () {
            var table = layui.table;
            var laytpl = layui.laytpl;
            var element = layui.element;
            var form = layui.form;
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
                , url: '/admin/list' //数据接口
                , page: true //开启分页
                , cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}
                    , {
                        field: 'id', title: 'ID', width: '15%', sort: true, fixed: 'left', templet: function (d) {
                            return d.id;//long 转Stirng
                        }
                    }
                    ,
                    {field: 'name', title: '用户名', width: '10%'}
                    ,
                    {
                        field: 'sex', title: '性别', width: '5%'
                        , templet: function (d) {
                            switch (d.sex) {
                                case 0:
                                    return '男';
                                case 1:
                                    return '女';

                            }
                        }
                    }
                    ,
                    {field: 'mobile', title: '手机号', width: '10%'}
                    , {field: 'email', title: '邮箱', width: '10%'}
                    , {field: 'roleName', title: '角色名称', width: '10%'}
                    , {field: 'remark', title: '备注', width: '17%'}
                    , {
                        field: 'status', title: '状态', width: '5%'
                        , templet: function (d) {
                            switch (d.status) {
                                case 0:
                                    return '启用';
                                case 1:
                                    return '停用';

                            }
                        }
                    }
                    , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: '15%'}


                ]]

            });

            function deleteByIds(ids) {
                layer.confirm('真的删除这些数据么', function (index) {
                    $.ajax({
                        "url": "/admin/delete",
                        "data": {
                            ids: ids
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

//头工具栏事件
            table.on('toolbar(test)', function (obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {
                    case 'add':
                        addOrUpdate({});
                        break;
                    case 'getCheckData':
                        var data = checkStatus.data;
                        if (data == null || data.length === 0) {
                            layer.msg("请先选择需要删除的数据");
                        } else {
                            var arr = [];
                            for (var i = 0; i < data.length; i++) {
                                arr.push(data[i].id);
                            }
                            deleteByIds(arr.join(','));
                        }

                        break;

                }
                ;
            });

            function addOrUpdate(data) {
                var getTpl = document.getElementById("demo").innerHTML;
                $.ajax({
                    type: 'post',
                    url: '/admin/roleList',
                    contentType: 'application/json',
                    dataType: "json",
                    success: function (res) {
                        data.data = res.data;

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
                                    "url": "/admin/updateOrAdd",
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
                                });
                                return false;
                            });
                        });
                    }
                });
            }

            /*管理员-停用*/
            function stop(id,status){
                layer.confirm('确认要停用吗？' ,function(index){
                    //此处请求后台程序，下方是成功后的前台处理……
                    $.ajax({
                        type: 'post',
                        url: '/admin/updateStatus',
                        data:{
                            "id":id,
                            "status":status
                        },
                        dataType: 'json',
                        success: function (res) {
                            if (res.code === 200) {
                                layer.msg("已停用");
                                reload();
                            } else {
                                layer.msg(res.msg);
                            }
                        }
                    });

                });
            }

            /*管理员-启用*/
            function run(id,status){
                layer.confirm('确认要启用吗？',function(index){
                    //此处请求后台程序，下方是成功后的前台处理……
                    $.ajax({
                        type: 'post',
                        url: '/admin/updateStatus',
                        data:{
                            "id":id,
                            "status":status
                        },
                        dataType: 'json',
                        success: function (res) {
                            if (res.code === 200) {
                                layer.msg("已启用");
                                reload();
                            } else {
                                layer.msg(res.msg);
                            }
                        }
                    });
                });
            }

//监听行工具事件
            table.on('tool(test)', function (obj) {
                var data = obj.data;

                // console.log(data);
                if (obj.event === 'del') {
                    deleteByIds(data.id);
                } else if (obj.event === 'edit') {
                    addOrUpdate(data);
                }else if(obj.event === 'run'){
                    run(data.id,1);
                }else if(obj.event === 'stop'){
                    stop(data.id,0);
                }

            });
        });

    });


</script>
</body>
</html>
