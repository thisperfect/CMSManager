<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ofhi" uri="/WEB-INF/tlds/ofhi.tld"%>
<%@ page isELIgnored="false" %>
<div class="container">
    <div class="row header">
        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="#">主页</a></li>
                <li><a href="#">角色管理</a></li>
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
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe654;</i>新增
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe642;</i>修改
                </button>
                <button class="layui-btn layui-btn-primary layui-btn-small">
                    <i class="layui-icon">&#xe640;</i>删除
                </button>
            </div>
            <table class="table" lay-filter="roleTable">
                <thead>
                <tr>
                    <th lay-data="{checkbox:true}"></th>
                    <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
                    <th lay-data="{field:'name', width:100}">角色名称</th>
                    <th lay-data="{field:'description', width:150}">描述</th>
                    <th lay-data="{field:'rank',sort: true , width:100}">排序</th>
                    <th lay-data="{field:'createTime', width:120}">创建时间</th>
                    <th lay-data="{field:'createBy', width:120}">创建人</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${roles.list}" varStatus="i">
                    <tr>
                        <th lay-data="{checkbox:true}"></th>
                        <td>${i.index}</td>
                        <td>${item.name}</td>
                        <td>${item.description}</td>
                        <td>${item.rank}</td>
                        <td>${ofhi:formatDate(item.createTime,null)}</td>
                        <td>${item.createBy}</td>
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

        table.init('roleTable', {
            page: true
            ,even: true
            ,id: 'roleTable'
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