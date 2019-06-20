﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<!--[if IE 6]>
<script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>权限管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 权限管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<form class="Huiform" method="post" action="" target="_self">
			<input type="text" class="input-text" style="width:250px" placeholder="权限名称" id="" name="">
			<button type="submit" class="btn btn-success" id="1" name=""><i class="Hui-iconfont">&#xe665;</i> 搜权限节点</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="batchDeletes()" class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
			</a>
			<a href="javascript:;" onclick="admin_permission_add('添加权限节点','/page/admin-permission-add','','310')" class="btn btn-primary radius">
				<i class="Hui-iconfont">&#xe600;</i> 添加权限节点
			</a>
		</span>
	</div>
	<table class="table table-border table-bordered table-bg table-sort">
		<thead>
			<tr>
				<th scope="col" colspan="7">权限节点</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="40">ID</th>
				<th width="200">权限名称</th>
				<th>字段名</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody id="tbody">

		</tbody>
	</table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

	function authList() {
		$.ajax({
			type: 'post',
			url: '/auth/list',
			dataType: 'json',
			success: function (data) {
				console.log(data);
				for (var i = 0; i <data.data.length; i++) {
					var d = data.data[i];

					var j = i + 1;
					var li = "<tr class=\"text-c\">" +
							"<td><input type=\"checkbox\" value=\"" + d.id + "\" name=\"subcheck\"></td>" +
							"<td>" + j + "</td>" +
							"<td>" + d.name + "</td>" +
							"<td>" + d.flag + "</td>" +
							"<td>"+
							"<a title=\"编辑\" href=\"javascript:;\" onclick=\"admin_permission_edit('角色编辑','/page/admin-permission-add','1','','310')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>"+
							"<a title=\"删除\" href=\"javascript:;\" onclick=\"auth_del(this,"+ d.id +")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>" +
							"</tr>";
					$("#tbody").append(li);
				}
			},
			error:function(data) {
				console.log(data+"111");
			}
		});
	}
	authList();

	/*权限-删除*/
	function auth_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
			$.ajax({
				type: 'post',
				url: '/auth/delAuth',
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
				url:"/auth/batchDel",
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

	/*管理员-权限-添加*/
	function admin_permission_add(title,url,w,h){
		layer_show(title,url,w,h);
	}
	/*管理员-权限-编辑*/
	function admin_permission_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	}

	/*管理员-权限-删除*/
	function admin_permission_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
			$.ajax({
				type: 'POST',
				url: '',
				dataType: 'json',
				success: function(data){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				},
				error:function(data) {
					console.log(data.msg);
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

</script>
</body>
</html>