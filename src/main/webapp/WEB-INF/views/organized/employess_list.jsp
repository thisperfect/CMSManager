<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-9-26
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ofhi" uri="/WEB-INF/tlds/ofhi.tld"%>
<%@ page isELIgnored="false" %>
<div class="container">
    <div class="row header">
        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="#">主页</a></li>
                <li><a href="#">部门员工</a></li>
                <li class="active">列表</li>
            </ol>
            <fieldset class="fieldset">
                <legend class="legend">检索</legend>
                <div class="col-xs-12">
                    <form onsubmit="" class="advanced-search" role="form" action="" method="post" id="roleForm" >
                        <div class="dropdown pull-left p-0">
                            <button class="btn btn-default dropdown-toggle pull-right" type="button" id="advanced-box" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                高级搜索&nbsp;<span class="caret"></span>
                            </button>
                            <ul class="layui-form dropdown-menu dropdown-menu-left p-10 right-0px left-0px w-360px" aria-labelledby="advanced-box">
                                <li>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">输入框</label>
                                        <div class="input-block">
                                            <input name=""  placeholder="请输入标题" autocomplete="off" class="layui-input" type="text">
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">选择框</label>
                                        <div class="input-block">
                                            <select name="">
                                                <option value=""></option>
                                                <option value="0">北京</option>
                                                <option value="1">上海</option>
                                                <option value="2">广州</option>
                                                <option value="3">深圳</option>
                                                <option value="4">杭州</option>
                                            </select>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">时间</label>
                                        <div class="input-block">
                                            <input value="" placeholder="选择时间" id="date1" class="layui-input" />
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">时间范围</label>
                                        <div class="input-block">
                                            <input value="" placeholder="选择时间" id="date2" class="layui-input" />
                                        </div>
                                    </div>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li>
                                    <div class="pull-right">
                                        <button class="btn btn-default " type="reset">重置搜索</button>
                                        <button class="btn btn-default " type="submit">确 定</button>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="col-xs-8 p-0">
                            <div class=" col-xs-8 p-0">
                                <input type="text" class="form-control search-input" name="" placeholder="" value="">
                            </div>
                            <div class="col-xs-4">
                                <button type="submit" class="btn  btn-info">检索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </fieldset>
        </div>
    </div>
    <div class="row content">
        <div class="col-xs-12">
            <div class="layui-btn-group btn-block" id="roleBar">
                <button class="layui-btn layui-btn-primary layui-btn-small" id="add">
                    <i class="layui-icon">&#xe654;</i>新增
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe642;</i>修改
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe640;</i>删除
                </button>
                <%--<button class="layui-btn layui-btn-primary layui-btn-small pull-right border-0px" role="button" id="table-setting" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    <i class="fa fa-cog"></i>
                </button>
                <ul class="dropdown-menu postion-top-auto" aria-labelledby="table-setting">
                    <li><a><i class="fa fa-home"></i> 一 </a></li>
                </ul>--%>
            </div>
            <table id="employessTable">
                <thead>
                <tr >
                    <td></td>
                    <th>编号</th>
                    <th>姓氏</th>
                    <th>名称</th>
                    <th>性别</th>
                    <th>生日</th>
                    <th>入职日期</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${pageInfo.list}" varStatus="i">
                    <tr>
                        <td></td>
                        <td>${item.empNo}</td>
                        <td>${item.firstName}</td>
                        <td>${item.lastName}</td>
                        <td>${item.gender}</td>
                        <td>${ofhi:formatDate(item.birthDate,'yyyy-MM-dd')}</td>
                        <td>${ofhi:formatDate(item.hireDate,'yyyy-MM-dd')}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row fooget">
        <div class="col-xs-12" >

        </div>
    </div>
</div>
<script>
   layui.use([ 'laydate','form'], function(){

       $('#roleTable').bootstrapTable({
           url: '../employees/list.shtml',         //请求后台的URL（*）
           method: 'post',                      //请求方式（*）
           toolbar: '#roleBar',                //工具按钮用哪个容器
           striped: true,                      //是否显示行间隔色
           cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
           pagination: true,                   //是否显示分页（*）
           sortable: false,                     //是否启用排序
           sortOrder: "asc",                   //排序方式
           queryParams: $('#roleForm').serialize(),//传递参数（*）
           sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
           pageNumber:1,                       //初始化加载第一页，默认第一页
           pageSize: 10,                       //每页的记录行数（*）
           pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
           strictSearch: true,
           clickToSelect: true,                //是否启用点击选中行
           height: setting.height - 240,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
           uniqueId: "empNo",                     //每一行的唯一标识，一般为主键列
           cardView: false,                    //是否显示详细视图
           detailView: false,                   //是否显示父子表
           minimumCountColumns: 2,             //最少允许的列数
           search: false,  //是否显示搜索框功能
           showRefresh: true, //是否显示刷新功能
           showToggle: true,
           showColumns: true,
           //iconSize: 'outline',
           showExport: false,  //是否显示导出按钮
           exportTypes:['excel','pdf'],  //导出文件类型
           icons: {
               refresh: 'fa fa-refresh fa-fw',
               toggle: 'fa fa-list-alt fa-fw',
               columns: 'fa fa-columns fa-fw'
           },
           columns: [{
               checkbox: true
           },{
               field: 'empNo',
               title: '编号'
           }, {
               field: 'firstName',
               title: '姓氏'
           }, {
               field: 'lastName',
               title: '名字'
           }, {
               field: 'gender',
               title: '性别'
           }, {
               field: 'birthDate',
               title: '出生日期'
           }, {
               field: 'hireDate',
               title: '入职日期'
           }]
       });

        var laydate = layui.laydate;
        laydate.render({
            elem: '#date1'
            ,type: 'datetime'
            ,calendar: true
        });
        //日期范围
        laydate.render({
            elem: '#date2'
            ,range: '至'
            ,calendar: true
        });

    });
</script>