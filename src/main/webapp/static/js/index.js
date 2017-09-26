var active ;
layui.use([ 'element'], function(){
    var element = layui.element


    //选项卡事件
     active = {
        el:'left-tab-menu'
        ,tabAdd: function(_title, _content, _id, _icon){
            if (_icon) {
                _title = '<i class="'+_icon+'"></i>' + _title;
            }
            element.tabAdd(this.el, {
                title: _title
                ,content: _content
                ,id: _id
            });
            element.tabChange(this.el, _id);
        }
        ,tabDelete: function( _id){
            element.tabDelete(this.el, _id);
        }
        ,tabChange: function(_id){
            element.tabChange(this.el, _id);
        }
    };

    /**点击菜单行为**/
    $('ul.sidebar-menu li a[href!="#"]').click(function (event) {
        event.preventDefault();
        var $this = $(this);
        addContent($this, true);
    });

    $('ul.settings-menu li #loginOut').on('click',function () {
        alertMessage.autoConfirm('确定退出系统吗?','退出系统',function () {
            window.location.href = '../user/loginOut.shtml';
        });
    })


    //窗体改变大小的时候，内容主体高度随之改变
    $(window).on('resize',function() {
        var _height =  $(window).height();
       var _hrader = _height - $(".main-header").height();
        $('div.layui-tab-content').height(_height - 122);
        setting.height = _height;
        setting.width = $(window).width();
        $('.sidebar').height(_hrader);
        $('.slimScrollDiv').height(_hrader);
    }).resize();

    /***全屏事件***/
    $('#FullScreen').bind('click', function () {
        var fullscreenElement = document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement;
        if (fullscreenElement == null) {
            entryFullScreen();
            $(this).html('<i class="fa fa-compress fa-fw"></i>退出全屏')
        } else {
            exitFullScreen();
            $(this).html('<i class="fa fa-expand fa-fw"></i>全屏')
        }
    });

    /**监听(ALT + L)锁屏事件**/
    $(document).keydown(function (e) {
        if (e.altKey && e.which == 76) {
            //锁屏
            alert('锁屏')
        }
        if (e.which == 27) {
            var fullscreenElement = document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement;
            if (fullscreenElement == null) {
                $('#FullScreen').html('<i class="fa fa-compress fa-fw"></i>退出全屏')
            }
        }
    });
});

/**
 *
 * @date 2017-09-26
 * @autor laiz
 * 点击菜单添加主体内容，如果已经打开则切换到相应的选项卡中
 * @param $this 菜单按钮DOM
 * @param reset 是否刷新内容，true 为点导航进来，false 右键刷新选项卡进来
 * @param $content 添加的内容
 * @returns {boolean}
 */
var addContent = function ($this,reset, $content) {
    var _id = $this.attr('id');
    var _url = $this.attr('href');
    if (_id === '' || _id === null || _id === undefined) {
        _id =  _url.replace(/\./g, '').replace(/\//g, '_').replace(/\?/g, '_').replace(/,/g, '_').replace(/=/g, '_').replace(/&/g, '_');
    } else if (_id === 'bhome') {
        active.tabChange(_id);
        return false;
    }
    if ((reset) && $('ul#layui-tab-cs-menu li[lay-id='+ _id +']').length > 0) {
        active.tabChange(_id);
        return false;
    } else {
        $this.attr('id', _id);
    }
    $.ajax({
        url:_url,
        type:'post',
        async:false,
        contentType: "application/json",
        dataType:"html",
        beforeSend: function () {
            alertMessage.loading();
        },
        success:function(_result) {
            if ($content) {
                $content.html(_result);
            } else {
                var _title = $this.attr('title');
                var _icon = $this.attr('icon');
                active.tabAdd(_title,_result,_id,_icon);
            }
        },
        complete: function () {
            alertMessage.closeLoading();
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            alertMessage.error(textStatus + "：" + XMLHttpRequest.responseText);
        }
    })
}

/****头部搜索相关事件 开始*******/
new Vue({
    el:'div.navbar-search',
    data:{
        searchDataList:[],
        userInputSearch:'',
        now: -1//输入框里面的值的索引，0为列表第一项
    },
    methods:{
        search: function ($event) {
            if($event.keyCode == 38 || $event.keyCode == 40) return;
            if ($event.keyCode == 13) {
                alert(this.userInputSearch)
                this.userInputSearch = '';
            }
            this.$http.jsonp("https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su",{
                 params: { wd: this.userInputSearch }, jsonp: 'cb'})
                .then(function (result) {
                    this.searchDataList = result.body.s;
                },function (e) {
                    alert(e.status);
                })
         },
        next: function ($event) {
            this.now ++
            if(this.now == this.searchDataList.length){
                this.now = 0
            }
            this.userInputSearch = this.searchDataList[this.now]
        },
        last: function ($event) {
            this.now --
            if(this.now < 0){
                this.now = this.searchDataList.length -1
            }
            this.userInputSearch = this.searchDataList[this.now]
        },
        hideSeachContent: function () {
            this.searchDataList = [];
        }
    }
});
/****头部搜索相关事件 结束******/