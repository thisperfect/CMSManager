<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017\9\7 0007
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.bootcss.com/pace/1.0.2/themes/green/pace-theme-fill-left.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/pace/1.0.2/pace.min.js"></script>
    <%@ include file="../share/lib.jsp"%>
    <!-- CSS-->
    <link rel="stylesheet" type="text/css" href="${basePath}/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/index.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <%--<link rel="stylesheet" type="text/css" href="${basePath}/plugins/font-awesome/css/font-awesome.min.css">--%>

    <title>Admin | Home</title>

</head>
<body class="sidebar-mini fixed">
<div class="wrapper">
    <!-- Navbar-->
    <header class="main-header hidden-print">
        <a class="logo" href="index.html">Admin</a>
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a class="sidebar-toggle visible-xs-block" href="#" data-toggle="offcanvas"></a>
            <!-- Navbar Right Menu-->
            <div class="navbar-custom-menu">
                <ul class="top-nav">
                    <li class="hidden-xs">
                        <div class="form-group navbar-search">
                            <input  type="text" class="form-control navbar-search-input" v-model="userInputSearch" @keyup.stop="search($event)" @blur="hideSeachContent" @keydown.down="next" @keydown.up.prevent="last" placeholder="搜索菜单功能"/>
                            <i class="text-muted fa fa-search form-control-feedback"></i>
                            <div class="navbar-search-content" v-show="searchDataList.length > 0">
                                <ul>
                                    <li  v-for="(item, index) in searchDataList" :class="{active: index == now}">
                                        <a :href="item.href" :title="item.name" :icon="item.icon" v-text="item"><i :class="item.icon"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown hidden-xs">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cogs fa-lg fa-fw"></i></a>
                        <ul class="dropdown-menu settings-menu">
                            <li> <a id="FullScreen" href="#"><i class="fa fa-expand fa-fw"></i>全屏</a></li>
                            <li><a href="#"><i class="fa fa-trash-o fa-fw"></i>清除缓存</a></li>
                            <li> <a id="lock" href="#"><i class="fa fa-lock fa-fw"></i>锁屏（快捷键：ALT + L）</a></li>
                        </ul>
                    </li>
                    <!--Notification Menu-->
                    <li class="dropdown notification-menu"><a class="dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell-o fa-lg fa-fw"></i></a>
                        <ul class="dropdown-menu">
                            <li class="not-head">You have 4 new notifications.</li>
                            <li><a class="media" href="javascript:;"><span class="media-left media-icon"><span class="fa-stack fa-lg fa-fw"><i
                                    class="fa fa-circle fa-stack-2x text-primary"></i><i
                                    class="fa fa-envelope fa-stack-1x fa-inverse"></i></span></span>
                                <div class="media-body"><span class="block">Lisa sent you a mail</span><span class="text-muted block">2min ago</span>
                                </div>
                            </a></li>
                            <li><a class="media" href="javascript:;"><span class="media-left media-icon"><span class="fa-stack fa-lg"><i
                                    class="fa fa-circle fa-stack-2x text-danger"></i><i class="fa fa-hdd-o fa-stack-1x fa-inverse"></i></span></span>
                                <div class="media-body"><span class="block">Server Not Working</span><span class="text-muted block">2min ago</span>
                                </div>
                            </a></li>
                            <li><a class="media" href="javascript:;"><span class="media-left media-icon"><span class="fa-stack fa-lg"><i
                                    class="fa fa-circle fa-stack-2x text-success"></i><i class="fa fa-money fa-stack-1x fa-inverse"></i></span></span>
                                <div class="media-body"><span class="block">Transaction xyz complete</span><span
                                        class="text-muted block">2min ago</span></div>
                            </a></li>
                            <li class="not-footer"><a href="#">See all notifications.</a></li>
                        </ul>
                    </li>

                    <!-- User Menu-->
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <img class="img-circle pull-left" src="${basePath}/static/images/photo/avatar.jpg" alt="User Image" ><span class="username">John Doe</span> <i class="fa fa-angle-down"></i></a>

                        <ul class="dropdown-menu settings-menu">
                            <li><a href="page-user.html"><i class="fa fa-cog fa-lg"></i> 设 置</a></li>
                            <li><a href="#" id="loginOut"><i class="fa fa-sign-out fa-lg"></i> 退 出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Side-Nav-->
    <aside class="main-sidebar hidden-print">
        <section class="sidebar">
            <div class="user-panel hidden-xs">
                <a class="sidebar-toggle" href="#" data-toggle="offcanvas"><i class="fa fa-navicon" aria-hidden="true"></i></a>
            </div>
            <!-- Sidebar Menu-->
            <ul class="sidebar-menu">
                <li class="active"><a href="/home.shtml" id="bhome"><i class="fa fa-home fa-fw"></i><span>主 页</span></a></li>
                <li class="treeview"><a href="#"><i class="fa fa-th-list fa-fw"></i><span>基本元素</span><i class="fa fa-angle-right fa-fw"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="pages/form.html" ><i class="fa fa-gavel fa-fw"></i>表单</a></li>
                        <li><a href="table-data-table.html"><i class="fa fa-keyboard-o fa-fw"></i>导航栏</a></li>
                    </ul>
                </li>
                <li class="treeview"><a href="#"><i class="fa fa-user fa-fw"></i><span>用户管理</span><i class="fa fa-angle-right fa-fw"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="../user/list.shtml" title="用户列表" icon="fa fa-th-list fa-fw" ><i class="fa fa-th-list fa-fw"></i>用户列表</a></li>
                        <li><a href="../sys/role/list.shtml" title="角色列表" icon="fa fa-sliders fa-fw" ><i class="fa fa-sliders fa-fw"></i>角色列表</a></li>
                    </ul>
                </li>
                <li class="treeview"><a href="#"><i class="fa fa-share fa-fw"></i><span>组件</span><i class="fa fa-angle-right fa-fw"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="../system/icon.shtml" title="图标" icon="fa fa-anchor fa-fw"><i class="fa fa-anchor fa-fw"></i>图标</a></li>
                        <li><a href="../employees/list.shtml" title="员工列表" icon="fa flag-checkered fw"><i class="fa flag-checkered fw"></i>员工列表</a></li>
                        <li class="treeview"><a href="#"><i class="fa fa-calendar fa-fw"></i><span>搜索</span><i class="fa fa-angle-right fa-fw"></i></a>
                            <ul class="treeview-menu">
                                <li><a href="blank-page.html"><i class="fa fa-cogs fa-fw"></i>百度</a></li>
                                <li><a href="#"><i class="fa fa-flag-checkered fa-fw"></i><span>必应</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </section>
    </aside>
    <div class="content-wrapper">
        <div class="layui-tab layui-tab-card" lay-filter="left-tab-menu" lay-allowclose="true">
            <ul class="layui-tab-title" id="layui-tab-cs-menu">
                <li class="layui-this bhome" lay-id="bhome"><i class="fa fa-home fa-fw"></i>后台主页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div class="row text-muted">
                        <div class="col-md-8">
                            <fieldset class="time-fieldset">
                                <legend>时间</legend>
                                <div id="time" class="col-md-6 h1 text-center"></div>
                                <div class="col-md-6 year">
                                    <h2 class="text-center year-week">
                                        <i class="fa fa-calendar fa-fw"></i>
                                        <span id="dateYear"></span>&nbsp;&nbsp;<span id="week"></span></h2>
                                    <h5 class="text-center">
                                        <p>农历：<span id="lunarDay"></span></p>
                                    </h5>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-md-4">
                            <fieldset class="time-fieldset">
                                <legend>天气</legend>
                                <div  id="weather"></div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 成功提示开始 -->
    <div id="success-message">
        <i class="fa fa-check fa-fw"></i>
        <span>操作成功</span>
    </div>
<!-- 成功提示结束 -->

<!-- Javascripts-->
<%--<%@ include file="../share/cdn.jsp"%>--%>
<%@ include file="../share/js.jsp"%>
<%--layui 插件开始--%>
<script src="${basePath}/plugins/layui/layui.js"></script>
<%--菜单js--%>
<script src="${basePath}/static/js/main.js"></script>
<%--公用组件js--%>
<script src="${basePath}/static/js/common.js"></script>
<%--时间js--%>
<script src="${basePath}/static/js/now.time.js"></script>
<%--主页js--%>
<script src="${basePath}/static/js/index.js"></script>
<%--右键菜单js--%>
<script src="${basePath}/static/js/left-menu.min.js"></script>
<%--数据表格插件开始--%>
<link rel="stylesheet" href="${basePath}/plugins/bootstrap-table/bootstrap-table.css">
<script src="${basePath}/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${basePath}/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script>
    layui.use([ 'element'], function(){
        var element = layui.element
        findWeather('#weather');
        startTimer('#time');
        $("#dateYear").html(getDateTime());
        $("#lunarDay").html(showCal());
        $("#week").html(getWeek());
        isIEwarn();
        context.init({preventDoubleContext: true});
        context.attach('#layui-tab-cs-menu', [
            {
                header: '欢迎使用右键菜单'
            },
            {
                icon: 'fa fa-refresh',
                text: '刷新当前',
                action: function() {
                    var _id = utils.getCurrentTabId();
                    if ('bhome' === _id) {
                        return;
                    }
                    addContent($('#' + _id), false, utils.getCurrentTabContent());
                }
            },
            {
                icon: 'fa fa-times',
                text: '关闭当前',
                action: function() {
                    var _id = utils.getCurrentTabId();
                    if ('bhome' === _id) {
                        return;
                    }
                    element.tabDelete('left-tab-menu', _id);
                }
            },
            {
                icon: 'fa fa-times-circle',
                text: '关闭全部',
                action: function() {
                    $('ul#layui-tab-cs-menu li').each(function () {
                        var _id = $(this).attr('lay-id');
                        if (_id != 'bhome') {
                            element.tabDelete('left-tab-menu', _id);
                        }
                    })
                }
            },
            {
                icon: 'fa fa-long-arrow-right',
                text: '关闭左边',
                action: function() {
                    $('ul#layui-tab-cs-menu  li.layui-this').nextAll().each(function () {
                        var _id = $(this).attr('lay-id');
                        element.tabDelete('left-tab-menu', _id);
                    });
                }
            },
            {
                icon: 'fa fa-long-arrow-left',
                text: '关闭右边',
                action: function() {
                    $('ul#layui-tab-cs-menu  li.layui-this').prevAll().each(function () {
                        var _id = $(this).attr('lay-id');
                        if (_id != 'bhome') {
                            element.tabDelete('left-tab-menu', _id);
                        }
                    });
                }
            },
            {
                icon: 'fa fa-exchange',
                text: '关闭其他',
                action: function() {
                    var _currId = utils.getCurrentTabId();
                    $('ul#layui-tab-cs-menu li').each(function () {
                        var _id = $(this).attr('lay-id');
                        if (_id != 'bhome' && _id != _currId) {
                            element.tabDelete('left-tab-menu', _id);
                        }
                    });
                }
            }
        ]);


      //  alertMessage.ajaxDialog('../sys/role/list.shtml');
    });
</script>

</body>
</html>
