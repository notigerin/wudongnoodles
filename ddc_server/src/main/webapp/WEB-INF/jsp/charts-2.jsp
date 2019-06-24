<%--
  Created by IntelliJ IDEA.
  User: 16048
  Date: 2019/6/17
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript" src="/lib/echarts/echarts.js" ></script>
    <!--[if IE 6]>

    <script type="text/javascript" src="/lib/" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>折线图</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 统计管理 <span class="c-gray en">&gt;</span> 折线图 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

    <div id="container" style="min-width:700px;height:400px"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript">
    var userCityUrl = "/zxCount/charts_2";
    var option = {
        xAxis: {
            type: 'category',
            data: []
        },
        yAxis: {
            type: 'value'
        },
            splitLine: {
                show: true,
                //  改变轴线颜色
                lineStyle: {
                    // 使用深浅的间隔色
                    color: ['black']
                }
            },
        series: [{
            data: [],
            type: 'line'
        }]
    };

    drewUserCityEcharts("container", userCityUrl, option);
    function drewUserCityEcharts(id, url, option) {
        var myChart = echarts.init(document.getElementById(id));
        myChart.showLoading();

        var flag = loadUserCityData(url, option);
        console.log(flag);
        if (flag){
            setTimeout(function () {
                myChart.hideLoading();
            }, 1000);
            myChart.setOption(option);
        }
    }

    function loadUserCityData(url, option) {
        var flag = false;
        $.ajax({
            type: "post",
            async: false,
            url: url,
            dataType: "json",
            success: function success(result) {
                flag = true;
                console.log(result.data);
                for (var i = result.data.length - 1; i >= 0; i--) {
                    option.series[0].data.push(result.data[i].count2);
                }
                for (var i = result.data.length - 1; i >= 0; i--) {
                    option.xAxis.data.push(result.data[i].month2);
                }



                console.log(option.series[0].data);
                console.log(option.xAxis.data)


            }
        });
        return flag;
    }

</script>
</body>
</html>