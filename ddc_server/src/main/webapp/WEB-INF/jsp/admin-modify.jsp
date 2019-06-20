<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<title>添加管理员 - 管理员管理 </title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员：</label>
			<input type="hidden" value="${admin.id}" id="id" name="id"/>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${admin.name}" placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>初始密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" autocomplete="off" value="" placeholder="密码" id="password" name="password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" autocomplete="off"  placeholder="确认新密码" id="confirmPassword" name="confirmPassword">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">

					<div class="radio-box">
						<input name="sex" type="radio" id="sex-1" value="0" checked>
							<label for="sex-1">男</label>
					</div>
					<div class="radio-box">
						<input name="sex" type="radio" id="sex-2" value="1">
							<label for="sex-1">女</label>
					</div>

			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${admin.mobile}" placeholder="" id="mobile" name="mobile">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${admin.email}" placeholder="@" name="email" id="email">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">角色：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="selectRole" size="1" id="selectRole">
			</select>
			</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="remark" cols="" rows="" class="textarea" value="${admin.remark}" placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="$.Huitextarealength(this,100)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="button" id="modifyAdmin" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">

	// function findAdmin() {
	// 	var id = $.request.id;
	// 	$.ajax({
	// 		type: 'post',
	// 		url: '/admin/findAdmin',
	// 		data:{"id":id},
	// 		dataType: 'json',
	// 		success: function (data) {
	// 			console.log(data);
	// 			var d = data;
	//
	// 			roleList();
	// 		},
	// 		error:function(data) {
	// 			console.log(data+"111");
	// 		}
	// 	});
	// }
	// findAdmin();

	function roleList() {
		$.ajax({
			type: 'post',
			url: '/role/list',
			dataType: 'json',
			success: function (data) {
				console.log(data);
				for (var i = 0; i <data.data.length; i++) {
					var d = data.data[i];
					/*if(id!=null && id.equals(d.id)){
						var li = "<option value=\"" + d.id+ "\" name=\"roleId\" selected=\"selected\">" + d.name +"</option>"
					}*/
					var li = "<option value=\"" + d.id+ "\" name=\"roleId\">" + d.name +"</option>"
					$("#selectRole").append(li);
				}
			},
			error:function(data) {
				console.log(data+"111");
			}
		});
	}
	roleList();

	$("#modifyAdmin").click(function () {
		//获取值
		var id = $("#id").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var confirmPassword = $("#confirmPassword").val();
		var sex = $("input[type='radio']").val();
		var mobile = $("#mobile").val();
		var email = $("#email").val();
		var roleId = $("#selectRole").val();
		var remark = $("#remark").val();
		$.ajax({
			url:"/admin/modifyAdmin",
			type:"post",
			//注意序列化的值一定要放在最前面,并且不需要头部变量,不然获取的值得格式会有问题
			data:{
				"id":id,
				"name":name,
				"password":password,
				"confirmPassword":confirmPassword,
				"sex":sex,
				"mobile":mobile,
				"email":email,
				"roleId":roleId,
				"remark":remark
			},
			dataType:"json",
			success:function (data) {
				// alert(data.result);
				alert("修改管理员成功");
			}
		})
	})


	$(function(){
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});

		$("#list-Admin-add").validate({
			rules:{
				adminName:{
					required:true,
					minlength:4,
					maxlength:16
				},
				password:{
					required:true,
				},
				confirmPassword:{
					required:true,
					equalTo: "#password"
				},
				sex:{
					required:true,
				},
				phone:{
					required:true,
					isPhone:true,
				},
				email:{
					required:true,
					email:true,
				},
				RoleId:{
					required:true,
				},
			},
			onkeyup:false,
			focusCleanup:true,
			success:"valid",
			submitHandler:function(form){
				$(form).ajaxSubmit({
					type: 'post',
					url: "/admin/addAdmin" ,
					success: function(data){
						layer.msg('添加成功!',{icon:1,time:1000});
					},
					error: function(XmlHttpRequest, textStatus, errorThrown){
						layer.msg('error!',{icon:1,time:1000});
					}
				});
				var index = parent.layer.getFrameIndex(window.name);
				parent.$('.btn-refresh').click();
				parent.layer.close(index);
			}
		});
	});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>