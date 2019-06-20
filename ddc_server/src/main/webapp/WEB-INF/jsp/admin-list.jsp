﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/lib/html5shiv.js"></script>
<script type="text/javascript" src="/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入管理员名称" name="">
		<button type="submit" class="btn btn-success" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="batchDeletes()" class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
			</a>
			<a href="javascript:;" onclick="admin_add('添加管理员','/page/admin-add','800','500')" class="btn btn-primary radius">
				<i class="Hui-iconfont">&#xe600;</i> 添加管理员
			</a>
		</span>
	</div>
	<table class="table table-border table-bordered table-bg table-sort" id="AdminList">
		<thead>
			<tr>
				<th scope="col" colspan="9">员工列表</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="40">ID</th>
				<th width="150">登录名</th>
				<th width="90">手机</th>
				<th width="150">邮箱</th>
				<th>角色</th>
				<th width="130">加入时间</th>
				<th width="100">是否已启用</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody class="copy_tbody" id="tbody">

		</tbody>
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
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
	/*列表获取*/
	function adminList() {
		$.ajax({
			type: 'post',
			url: '/admin/list',
			dataType: 'json',
			success: function (data) {
				console.log(data);
				for (var i = 0; i <data.data.length; i++) {
					var d = data.data[i];
					if(d.status == 0){
						var status = "已启用";
						var unstatus = "停用";
						var staclass = "label label-success radius";
						var onclick = "admin_stop";
					}else{
						var status = "已停用";
						var unstatus = "启用";
						var staclass = "label radius";
						var onclick = "admin_start";
					}
					var j = i + 1;
					var li = "<tr class=\"text-c\">" +
							"<td><input type=\"checkbox\" value=\"" + d.id + "\" name=\"subcheck\"></td>" +
							"<td>" + j + "</td>" +
							"<td>" + d.name + "</td>" +
							"<td>" + d.mobile + "</td>" +
							"<td>" + d.email + "</td>" +
							"<td>" + d.roleName + "</td>" +
							"<td>" + d.createTime + "</td>" +
							"<td class=\"td-status\"><span class=\"" + staclass + "\">" + status + "</span></td>" +
							"<td class=\"td-manage\">"+
								"<a style=\"text-decoration:none\" onClick=\""+ onclick + "(this," + d.id + ","+ d.status +")\" href=\"javascript:;\" title=\"" + unstatus + "\"><i class=\"Hui-iconfont\">&#xe631;</i></a>"+
								"<a title=\"编辑\" href=\"javascript:;\" onclick=\"admin_edit('管理员编辑','/page/admin-modify?id="+ d.id +"&name="+ d.name +"&sex="+ d.sex +"&mobile="+ d.mobile +"&email=" + d.email+ "&roleId="+ d.roleId +"&remark="+ d.remark +"',"+ d.id +",'800','500')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>"+
								"<a title=\"删除\" href=\"javascript:;\" onclick=\"admin_del(this," + d.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>"+
							"</td>" +
							"</tr>";
					$("#tbody").append(li);
				}
			}
		});
	}
	adminList();



	/*管理员-增加*/
	function admin_add(title,url,w,h){
		layer_show(title,url,w,h);
	}

	/*管理员-编辑*/
	function admin_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	}

	/*-删除*/
	function admin_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
			$.ajax({
				type: 'post',
				url: '/admin/delAdmin',
				data:{
					"id":id
				},
				dataType: 'json',
				success: function(data){
					$(obj).parents("tr").remove();
					location.reload();
					layer.msg('已删除!',{icon:1,time:1000});
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		});
	}
	/*批量删除*/
	function batchDeletes(){
		var checkedNum = $("input[name='subcheck']:checked").length;
		if(checkedNum==0){
			alert("请至少选择一项!");
			return false;
		}
		if(confirm("确定删除所选项目?")){
			var checkedList = new Array();
			$("input[name='subcheck']:checked").each(function(){
				checkedList.push($(this).val());
			});
			$.ajax({
				type:"POST",
				url:"/admin/batchDel",
				data:{"delItems":checkedList.toString()},
				datatype:"json",
				success:function(data){
					$("[name='checkbox2']:checkbox").attr("checked",false);
					location.reload();
					art.dialog.tips('删除成功!');
				},
				error:function(data){
					art.dialog.tips('删除失败!');
				}
			});
		}
	}

	/*管理员-停用*/
	function admin_stop(obj,id,status){
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
				success: function(data){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,"+ sid +")" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已停用</span>');
					$(obj).remove();
					location.reload();
					layer.msg("已停用",{icon: 5,time:1000});
				},
				error:function(data) {
					console.log(data.data.msg);
				},
			});

		});
	}

	/*管理员-启用*/
	function admin_start(obj,id,status){
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
				success: function(data){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
					$(obj).remove();
					location.reload();
					layer.msg('已启用!', {icon: 6,time:1000});
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		});
	}

	/*分页处理*/
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
</script>
</body>
</html>