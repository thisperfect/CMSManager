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
                                <button class="btn btn-default " type="button">确 定</button>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="col-xs-8 p-0">
                    <div class=" col-xs-8 p-0">
                        <input type="text" class="form-control search-input" name="" placeholder="" value="">
                    </div>
                    <div class="col-xs-4">
                        <button type="submit" class="btn  btn-info ">检索</button>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
</div>
<script>
    layui.use('laydate', function(){
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