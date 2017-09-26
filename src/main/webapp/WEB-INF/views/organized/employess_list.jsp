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
                    <form onsubmit="" class="advanced-search" role="form" action="" method="post" id="pagerForm" >
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
                                            <select name="" >
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
                                            <input value="" placeholder="选择时间" id="date1" class="layui-input"/>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">时间范围</label>
                                        <div class="input-block">
                                            <input value="" placeholder="选择时间" id="date2" class="layui-input"/>
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
            <div class="layui-btn-group btn-block">
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe654;</i>新增
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe642;</i>修改
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe640;</i>删除
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-small pull-right border-0px" role="button" id="table-setting" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    <i class="fa fa-cog"></i>
                </button>
                <ul class="dropdown-menu postion-top-auto" aria-labelledby="table-setting">
                    <li><a><i class="fa fa-home"></i> 一 </a></li>
                </ul>
            </div>
            <table  class="layui-table" lay-filter="test">
                <thead>
                <tr  class="success">
                    <th lay-data="{field:'id', width:100, sort: true}">序号</th>
                    <th lay-data="{field:'empNo', width:150, sort: true}">编号</th>
                    <th lay-data="{field:'firstName', width:150}">姓氏</th>
                    <th lay-data="{field:'lastName', width:150}">名称</th>
                    <th lay-data="{field:'gender', width:100}">性别</th>
                    <th lay-data="{field:'birthDate', width:150}">生日</th>
                    <th lay-data="{field:'hireDate', width:150}">入职日期</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${pageInfo.list}" varStatus="i">
                    <tr>
                        <th>${i.index}</th>
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
        <div class="col-xs-12">

        </div>
    </div>
</div>
<script>
    layui.use([ 'laypage', 'table','laydate'], function(){
        var laypage = layui.laypage
            ,table = layui.table
            ,laydate = layui.laydate;

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

        table.init('test', {

        });


        /* table.on('tool(roleOperationBtn)', function(obj){
         var data = obj.data
         ,layEvent = obj.event;
         if(layEvent === 'detail'){

         } else if(layEvent === 'del'){
         layer.confirm('真的删除行么', function(index){
         obj.del();

         });
         } else if(layEvent === 'edit'){

         }
         });*/
        laypage.render({
            elem: 'rolePage'
            ,count: 100
            ,skin: '#1E9FFF'
            //,skip: true
            ,jump: function(obj, first){
                if(!first){
                    layer.msg('第'+ obj.curr +'页');
                }
            }
        });
    });
</script>