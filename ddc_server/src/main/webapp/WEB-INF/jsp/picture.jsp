<%--
  Created by IntelliJ IDEA.
  User: muqi1118
  Date: 2019/6/29
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
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
    <link rel="stylesheet" href="/lib/layui/css/layui.css" media="all">
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script src="/layui/layui.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>图片列表</title>
</head>
<body>
<div class="page-container">
    <div class="text-c"> 日期范围
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'end\')||\'%y-%M-%d\'}' })" id="start"
               class="input-text Wdate" style="width:120px;" name="start"/>
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'start\')}',maxDate:'%y-%M-%d' })" id="end"
               class="input-text Wdate" style="width:120px;" name="end"/>
        <input type="text" class="input-text" style="width:250px" placeholder="输入图片名称" id="keywords" name="keywords"/>
        <button type="button" class="btn btn-success radius" id="search" name="search"><i
                class="Hui-iconfont">&#xe665;</i> 搜索
        </button>
    </div>
    <table class="table table-border table-bordered table-hover table-bg table-sort" lay-filter="test"
           style="margin-top: 10px;">

    </table>
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
<script type="text/javascript" src="/lib/layui/layui.all.js"></script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">批量删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>

    </div>
</script>
<script type="text/html" id="barDemo">
    {{# if(d.status===0){ }}
    <a class="layui-btn layui-btn-xs layui-bg-gray" lay-event="stop" title="上架"><i class="layui-icon layui-icon-face-surprised" style="font-size: 30px; color: #3f324d;"> 上架 </i></a>
    {{# }else{ }}
    <a class="layui-btn layui-btn-xs layui-bg-red" lay-event="run" title="下架"><i class="layui-icon layui-icon-fire" style="font-size: 30px; color: #00a5ff;"> 下架 </i></a>
    {{# } }}
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript">
    $(function () {
        layui.use(['table', 'laytpl', 'element', 'form', 'upload'], function () {
            var table = layui.table;
            var laytpl = layui.laytpl;
            var element = layui.element;
            var form = layui.form;
            var jquery = layui.jquery
            var upload = layui.upload;
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
                , url: '/picture/list' //数据接口
                , page: true //开启分页
                , cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}
                    , {
                        field: 'id', title: 'ID', width: '15%', sort: true, fixed: 'left', templet: function (d) {
                            return d.id;//long 转Stirng
                        }
                    }
                    , {field: 'sort', title: '分类名', width: '10%'}
                    , {
                        field: 'image', title: '图片封面', width: '10%'
                        ,templet:function (d) {
                            return '<img class="avatar size-XL l" src="'+ d.image +'">'
                        }
                    }

                    , {
                        field: 'name', title: '图片名', width: '10%'
                        ,templet:function (d) {
                            return '<a lay-event="show">'+d.name +'<a>'
                        }
                    }
                    ,
                    {field: 'tags', title: '标签', width: '5%'}
                    , {field: 'updateTime', title: '更新时间', width: '10%'}
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
                        "url": "/picture/delete",
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

                };
            });

            function addOrUpdate(data) {
                $.ajax({
                    "url": "/picture/list",
                    "data": JSON.stringify(data.field),
                    type: "post",
                    contentType: 'application/json',
                    dataType: "json",
                    success: function (res) {
                        console.log(res)
                        if (res.code === 200) {

                            layer.msg("操作成功");
                            reload();
                            layer.close(index);
                        } else {
                            layer.msg(res.msg);
                        }
                    }
                });


                var getTpl = document.getElementById("demo").innerHTML;

                laytpl(getTpl).render(data, function (html) {
                    var index = layer.open({
                        type: 1,
                        content: html,
                        area: ['500px', '600px']
                    });
                    form.render();
                    // //普通图片上传
                    // var uploadInst = upload.render({
                    //     elem: '#test1'
                    //     ,url:'/member/upload'
                    //     ,accept:'images'
                    //     ,done: function(res){
                    //         console.log(res);
                    //         document.getElementById("icon").value = res.filePath;
                    //     }
                    // });
                    form.on('submit(update_form_submit)', function (data) {
                        layer.msg(JSON.stringify(data.field));
                        $.ajax({
                            "url": "/picture/updateOrAdd",
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


            function showPicture(data){
                var getTpl = document.getElementById("showPicture").innerHTML;

                laytpl(getTpl).render(data, function (html) {
                    var index = layer.open({
                        type: 1,
                        content: html,
                        area: ['500px', '600px']
                    });
                    form.render();
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
                }else if(obj.event === 'editPassword'){
                    editPassword(data);
                }else if(obj.event === 'show'){
                    showMember(data);
                }

            });
        });
        /*会员-停用*/
        function stop(id,status){
            layer.confirm('确认要停用吗？' ,function(index){
                //此处请求后台程序，下方是成功后的前台处理……
                $.ajax({
                    type: 'post',
                    url: '/picture/updateStatus',
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

        /*会员-启用*/
        function run(id,status){
            layer.confirm('确认要启用吗？',function(index){
                //此处请求后台程序，下方是成功后的前台处理……
                $.ajax({
                    type: 'post',
                    url: '/picture/updateStatus',
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
    });
</script>

</body>
</html>
