<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<link rel="stylesheet" href="/lib/layui/css/layui.css" media="all">
<!--[if IE 6]>
<script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script src="/layui/layui.js"></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员中心 <span class="c-gray en">&gt;</span> 会员管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入会员名称、电话、邮箱" id="searchInput" name="searchInput">
		<button type="submit" class="btn btn-success radius" id="search" name="search" onclick="member_search()"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="member_add('添加用户','/member/add','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span> <span class="r">共有数据：<strong class="total"></strong> 条</span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort" id="member_list">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">ID</th>
				<th width="100">用户名</th>
				<th width="40">性别</th>
				<th width="90">手机</th>
				<th width="150">邮箱</th>
				<th width="">地址</th>
				<th width="130">加入时间</th>
				<th width="70">状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody id="jilu"></tbody>
	</table>

	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script>
	/*用户列表查询*/
		$.ajax({
			type: 'POST',
			url: '/member/memberList',
			dataType: 'json',
			success: function(data){
				var total=document.getElementsByClassName("total")[0];
				total.innerHTML=data.data.length;
				//window.location.reload();
				console.log(data);
				for (var i = 0; i <data.data.length ; i++) {
					var d=data.data[i];
					if(d.gender==1){
						var sex='男';
					}else if(d.gender==2){
						var sex='女';
					}else{
						var sex='保密';
					}
					var li='<tr class="text-c">\n' +
							'<td><input type="checkbox" value="1" name=""></td>\n' +
							'<td>'+d.id+'</td>\n' +
							'<td><u style="cursor:pointer" class="text-primary" onclick="member_show(this,\'360\',\'400\')">'+d.username+'</u></td>\n' +
							// '<td><u style="cursor:pointer" class="text-primary" onclick="member_show(\'张三\',\'/member/show\',\'10001\',\'360\',\'400\')">'+d.username+'</u></td>\n' +
							'<td>'+sex+'</td>\n' +
							'<td>'+d.telephone+'</td>\n' +
							'<td>'+d.postAddress+'</td>\n' +
							'<td class="text-l">'+d.address+'</td>\n' +
							'<td>'+d.createTime+'</td>\n' +
							'<td class="td-status"><span class="label label-success radius">已启用</span></td>\n' +
							'<td class="td-manage"><a style="text-decoration:none" onClick="member_stop(this,\'10001\')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="member_edit(this,\'\',\'510\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password(\'修改密码\',\'change-password.html\',\'10001\',\'600\',\'270\')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>\n' +
							'</tr>';
					$("#jilu").append(li);
				}
			},
			error:function(data) {
				console.log(data);
			}
		});

</script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>

<%--<script>--%>
<%--	layui.use('table', function(){--%>
<%--		var table = layui.table;--%>

<%--		//第一个实例--%>
<%--		table.render({--%>
<%--			elem: '#member_list'--%>
<%--			,height: 312--%>
<%--			,url: '/member/memberList' //数据接口--%>
<%--			,page: true //开启分页--%>
<%--			,cols: [[ //表头--%>
<%--				{type: 'checkbox', fixed: 'left'},--%>
<%--				{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}--%>
<%--				,{field: 'username', title: '用户名', width:80}--%>
<%--				,{field: 'sex', title: '性别', width:80, sort: true}--%>
<%--				,{field: 'mobile', title: '手机', width:80}--%>
<%--				,{field: 'postAddress', title: '邮箱', width: 80, sort: true}--%>
<%--				,{field: 'address', title: '地址', width: 177}--%>
<%--				,{field: 'createTime', title: '加入时间', width: 80, sort: true}--%>
<%--				,{field: 'status', title: '状态', width: 80}--%>
<%--				,{field: 'operator', title: '操作', width: 135, sort: true}--%>
<%--			]]--%>
<%--		});--%>

<%--	});--%>
<%--</script>--%>

<script type="text/javascript">
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(obj,w,h){
	var id=obj.parentNode.parentNode.childNodes[3].innerText;
	console.log(id);
		$.ajax({
			type: 'POST',
			url: '/member/memberShow?id='+id,
			dataType: 'json',
			success: function(data){
				console.log(data);
				var d=data.data;
				if(d.gender==1){
					var sex='男';
				}else if(d.gender==2){
					var sex='女';
				}else{
					var sex='保密';
				}
				var id=d.id;
				var username=d.username;
				var telephone=d.telephone;
				var postAddress=d.postAddress;
				var address=d.address;
				var createTime=d.createTime;
				layer_show(username,'/member/show?id='+id+'&&username='+username+'&&gender='+sex+
									'&&telephone='+telephone+'&&postAddress='+postAddress+'&&address='+
									address+'&&createTime='+createTime,w,h);
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}


/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*用户-编辑*/
function member_edit(obj,w,h){
	var id=obj.parentNode.parentNode.childNodes[3].innerText;
	console.log(id);
	$.ajax({
		type: 'POST',
		url: '/member/memberEdit?id='+id,
		dataType: 'json',
		success: function(data){
			console.log(data);
			var d=data.data;
			var id=d.id;
			var username=d.username;
			var gender=d.gender;
			var telephone=d.telephone;
			var postAddress=d.postAddress;
			var address=d.address;
			var city=d.city;
			layer_show(username,'/member/edit?id='+id+'&&username='+username+'&&gender='+gender+
					'&&telephone='+telephone+'&&postAddress='+postAddress+'&&address='+
					address+'&&city='+city,w,h);
		},
		error:function(data) {
			console.log(data.msg);
		}
	});
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj){
	var id=obj.parentNode.parentNode.childNodes[3].innerText;
	console.log(id);
	var index=layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/member/deleteMember?id='+id,
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.closeAll();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
function member_search() {
	var search=document.getElementById("searchInput").value;
	$.ajax({
		type: 'POST',
		url: '/member/memberSearch?search='+search,
		dataType: 'json',
		success: function(data){
			//window.location.reload();
			console.log(data);
			for (var i = 0; i <data.data.length ; i++) {
				var d=data.data[i];
				if(d.gender==1){
					var sex='男';
				}else if(d.gender==2){
					var sex='女';
				}else{
					var sex='保密';
				}
				var li='<tr class="text-c">\n' +
						'<td><input type="checkbox" value="1" name=""></td>\n' +
						'<td>'+d.id+'</td>\n' +
						'<td><u style="cursor:pointer" class="text-primary" onclick="member_show(\'张三\',\'member-show.html\',\'10001\',\'360\',\'400\')">'+d.username+'</u></td>\n' +
						'<td>'+sex+'</td>\n' +
						'<td>'+d.telephone+'</td>\n' +
						'<td>'+d.postAddress+'</td>\n' +
						'<td class="text-l">'+d.address+'</td>\n' +
						'<td>'+d.createTime+'</td>\n' +
						'<td class="td-status"><span class="label label-success radius">已启用</span></td>\n' +
						'<td class="td-manage"><a style="text-decoration:none" onClick="member_stop(this,\'10001\')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="member_edit(\'编辑\',\'member-add.html\',\'4\',\'\',\'510\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password(\'修改密码\',\'change-password.html\',\'10001\',\'600\',\'270\')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>'+
						'</tr>';
				$('#jilu').empty();
				$("#jilu").append(li);
			}
		},
		error:function(data) {
			console.log(data);
		}
	});
}
setTimeout(function () {
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		]
	});
},150)
</script> 
</body>
</html>