var setting = {
    width: $(window).width(),
    height: $(window).height(),
    loadingHtml: '<div id="loading-box"><div class="layer"></div><img src="../static/images/loaders/33.gif"></div>',
    dataTableOps:{
        classes:'table table-hover',
        height:this.height - 240,
        striped:false,//设置为 true 会有隔行变色效果
        iconsPrefix:'fa',
        icons: {
            refresh: 'fa fa-refresh fa-fw',
            toggle: 'fa fa-list-alt fa-fw',
            columns: 'fa fa-columns fa-fw'
        },
        method:'get',
        cache:true,
        contentType:'application/json;charset=UTF-8',
        dataType:'json',
        ajaxOptions:{//提交ajax请求时的附加参数
            statusCode: {
                404: function() {
                    alertMessage.error('未找到资源，请检查请求结构是否正确。')
                },
                500: function () {
                    alertMessage.error('服务器繁忙请稍后重试。')
                }
            },
            beforeSend:function () {
                alertMessage.loading();
            },
            complete:function () {
                alertMessage.closeLoading();
            },
            error:function (XMLHttpRequest,  textStatus,  errorThrown) {
                console.log(errorThrown);
                alertMessage.closeLoading();
                alertMessage.error(XMLHttpRequest.status + ':' + textStatus);
            }
        },
        queryParams:function (params) {
            return params;
        },
        queryParamsType:'limit',
        responseHandler:function (res) {//加载服务器数据之前的处理程序，可以用来格式化数据。
        },
        pagination:true,//设置为 true 会在表格底部显示分页条
        paginationLoop:true,//	设置为 true 启用分页条无限循环的功能。
        onlyInfoPagination:true,//设置为 true 只显示总数据数，而不显示分页按钮。需要 pagination='True'
        sidePagination:'server',
        pageNumber:1,
        pageSize:15,
        pageList:[10, 25, 50, 100, 200],
        selectItemName:'',//radio or checkbox 的字段名
        smartDisplay:true,//正确显示分页或刷卡视图。
        escape:false,//转义HTML字符串，替换 &, <, >, ", `, 和 ' 字符.
        search:false,//	是否启用搜索框
        searchOnEnterKey:false,//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
        strictSearch:false,//设置为 true启用 全匹配搜索，否则为模糊搜索
        searchText:'输入按回车搜索',//初始化搜索文字
        searchTimeOut:500,//	设置搜索超时时间
        trimOnSearch:false,//设置为 true 将允许空字符搜索
        showHeader:true,//是否显示列头
        showFooter:false,//是否显示列脚
        showColumns:false,//是否显示 内容列下拉框
        showRefresh:true,//是否显示 刷新按钮
        showToggle:true,//是否显示 切换试图（table/card）按钮
        showPaginationSwitch:true,//是否显示 数据条数选择框
        minimumCountColumns:1,//当列数小于此值时，将隐藏内容列下拉框。
        idField:'id',//指定主键列
        uniqueId:'id',//为每一行表示一个惟一的标识符。
        cardView:false,//设置为 true将显示card视图，适用于移动设备。否则为table试图，适用于pc
        detailView:false,//	设置为 true 可以显示详细页面模式。
        detailFormatter:function(index, row) {
            return '';
        },//格式化详细页面模式的视图。
        searchAlign:'right',//指定 搜索框 水平方向的位置。'left' or 'right'
        buttonsAlign:'right',//指定 按钮 水平方向的位置。'left' or 'right'
        toolbarAlign:'left',//指定 toolbar 水平方向的位置。'left' or 'right'
        paginationVAlign:	'bottom',//指定 分页条 在垂直方向的位置。'top' or 'bottom' or 'bonth'
        paginationHAlign:'right',//指定 分页条 在水平方向的位置。'left' or 'right'
        paginationDetailHAlign:'left',//指定 分页详细信息 在水平方向的位置。'left' or 'right'
        paginationPreText:'上一页',//指定分页条中上一页按钮的图标或文字
        paginationNextText:'下一页',//指定分页条中下一页按钮的图标或文字
        clickToSelect:true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
        singleSelect:false,//	设置True 将禁止多选
        toolbar:'#roleBar',//一个jQuery 选择器，指明自定义的toolbar 例如:#toolbar, .toolbar.
        checkboxHeader:true,//设置false 将在列头隐藏check-all checkbox .
        maintainSelected:true,//设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项
        sortable:true,//设置为false 将禁止所有列的排序
        silentSort:false,//设置为 false 将在点击分页按钮时，自动记住排序项。仅在 sidePagination设置为 server时生效.
        rowStyle:function(row,index) {
            return '';
        },//自定义行样式 参数为：row: 行数据 index: 行下标 返回值可以为class或者css
        rowAttributes:function(row,index) {
            return '';
        }//自定义行属性 参数为：row: 行数据  index: 行下标  返回值可以为class或者css 支持所有自定义属性
    }
}

var utils =  {
    getCurrentTabId: function () {
        return $('ul#layui-tab-cs-menu  li.layui-this').attr('lay-id');
    },
    getCurrentTabContent: function () {
        return $('div.content-wrapper > div.layui-tab-card div.layui-tab-content div.layui-show');
    },
    //返回id查找原素，没有找到时，防止为空，会构造一个
    id:function(str){
        return document.getElementById(str) || document.createElement('span');
    },
    /**
     * 手机号验证
     * @param str
     * @returns {boolean}
     */
    isPhoneNum:function(str) {
        var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
        return reg.test(str);
    },
    /**
     * 读取COOKIE
     */
    getCookie: function(name) {
        var reg = new RegExp("(^| )" + name + "(?:=([^;]*))?(;|$)"), val = document.cookie.match(reg);
        return val ? (val[2] ? unescape(val[2]).replace(/(^")|("$)/g,"") : "") : null;
    },
    /**
     *
     * @param name
     * @param value
     * @param expires  单位是分钟  传0可清除cookie
     * @param path
     * @param domain
     * @param secure
     */
    setCookie: function(name, value, expires, path, domain, secure) {
        var exp = new Date(), expires = expires || null, path = path || "/", domain = domain || '1-dian.net', secure = secure || false;
        expires ? exp.setMinutes(exp.getMinutes() + parseInt(expires)) : "";
        document.cookie = name + '=' + escape(value) + ( expires ? ';expires=' + exp.toGMTString() : '') + ( path ? ';path=' + path : '') + ( domain ? ';domain=' + domain : '') + ( secure ? ';secure' : '');
    },
    /**
     * 去掉字符串两端空格
     * @param str
     */
    trim: function (str){
        return str.replace(/(^\s*)|(\s*$)/g, "");
    },
    /**
     * 获取一个url上的参数
     * @param e 参数名称
     * @param t url  默认为当前url
     * @returns {string}
     */
    getParam:function (e,t){  //获取url参数
        var n=arguments[1]||window.location.search,r=new RegExp("(^|&)"+e+"=([^&]*)(&|$)","i"),i=n.substr(n.indexOf("?")+1).match(r);
        return i!=null?i[2]:"";
    },
    /**
     * 改变url中的参数值
     */
    appendParam: function (url,arg,arg_val){
        var pattern=arg+'=([^&]*)';
        var replaceText=arg+'='+arg_val;
        if(url.match(pattern)){
            var tmp='/('+ arg+'=)([^&]*)/gi';
            tmp=url.replace(eval(tmp),replaceText);
            return tmp;
        }else{
            if(url.match('[\?](.+)')){
                return url + '&' + replaceText;
            } else if(url.match('[\?]')) {
                return url + replaceText;
            } else{
                return url + '?' + replaceText;
            }
        }
    }
}

function isIEwarn() {
    var userAgent = navigator.userAgent;
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1;
    //  var isEdge = userAgent.indexOf("Edge") > -1 && !isIE;
    //var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if (isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if (fIEVersion <= 9) {
            alertMessage.warning('您的浏览器太过古老，部分功能将不能使用。为了您更好的体验全部功能推荐下载最新的其它浏览器。', {
                buttons: {
                    cancel: {
                        text: '朕知道了',
                        btnClass: 'btn-danger'
                    },
                    firefox: {
                        text: '去下载火狐',
                        btnClass: 'btn-info',
                        action: function () {
                            window.open('https://www.mozilla.org/zh-CN/firefox/new/?scene=2', 'target="_blank"');
                        }
                    },
                    chrome: {
                        text: '去下载火狐',
                        btnClass: 'btn-success',
                        action: function () {
                            window.open('http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html#eula', 'target="_blank"');
                        }
                    }
                }
            });
        } else {
            alertMessage.warning('您当前使用是的IE浏览器，为了您更好的体验全部功能推荐使用非IE浏览器。')
        }
    }
}

/**
 * 开启全屏
 */
function entryFullScreen() {
    var docE = document.documentElement;
    if (docE.requestFullScreen) {
        docE.requestFullScreen()
    } else if (docE.mozRequestFullScreen) {
        docE.mozRequestFullScreen()
    } else if (docE.webkitRequestFullScreen) {
        docE.webkitRequestFullScreen()
    }
}
/**
 * 退出全屏
 */
function exitFullScreen() {
    var docE = document;
    if (docE.exitFullscreen) {
        docE.exitFullscreen()
    } else if (docE.mozCancelFullScreen) {
        docE.mozCancelFullScreen()
    } else if (docE.webkitCancelFullScreen) {
        docE.webkitCancelFullScreen()
    }
}

/**
 * 获取当前定位城市天气
 * 定位失败获取默认城市‘北京’
 */
function findWeather(select) {
    var cityUrl = 'http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js';
    $.getScript(cityUrl, function (script, textStatus, jqXHR) {
        var citytq = '北京';
        if ('success' === textStatus) {
            citytq = remote_ip_info.city;
        }
        var url = "http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&city=" + citytq + "&day=0&dfc=3";
        $.ajax({
            url: url,
            dataType: "script",
            scriptCharset: "gbk",
            success: function (data) {
                var _w = window.SWther.w[citytq][0];
                var _f = _w.f1 + ".png";
                if (new Date().getHours() > 17) {
                    _f = _w.f2 + "b.png";
                }
                var html = '<div class="col-sm-4"> <img class="img-rounded img-responsive" src="../static/images/weather/weather_' + _f + '"/>';
                html += ' </div><div class="col-sm-8"><h2><i class="fa fa-map-marker fa-fw h6"></i>' + citytq + '<span>' + _w.s1 + '</span></h2>';
                html += '<h5>温度：' + _w.t2 + '℃～' + _w.t1 + '℃</h5><h5>' + _w.d1 + _w.p1 + '级</h5></div>';
                $(select).html(html);
            }, error: function () {
                $('#weather').html('<P class="text-muted text-center">获取天气信息失败......</P>');
            }
        });
    });
}

var ajax =  {
    ajaxRequest:function(obj, _callback){
        obj.success = function(json){
            var code = json['code'] ;
            switch (code) {
                case 10000:
                    if (json['msg']) {
                        alertMessage.success(json['msg']);
                    }
                    json = JSON.stringify(json);
                    runCallback(_callback, json);
                    break;
                case 20007:
                    alertMessage.info('登入过期，请先登入。',{
                        autoClose: 'btnOk|3000' ,
                        buttons: {
                            btnOk: {
                                text: '现在登入',
                                btnClass: 'btn-info',
                                keys: ['enter'],
                                action: function () {
                                    runCallback(_callback)
                                }
                            }
                        }
                    }, function () {
                       window.location.href = '/system/login.shtml';
                    });
                    break;
                default:
                   alertMessage.error(code + ':' + json['msg'])
                    break;
            }
        };

        obj.statusCode= {
            404:function(){alertMessage.warning('未找到资源，请检查请求结构是否正确。')},
            500:function(){alertMessage.error('服务器繁忙' );}
        };

        obj.error = function(XMLHttpRequest, textStatus, errorThrown){
            alertMessage.error(XMLHttpRequest.status + ':' + textStatus);
            console.log(errorThrown);
        };
        obj.beforeSend = function () {
            alertMessage.loading();
        };
        obj.complete = function () {
            alertMessage.closeLoading();
        };
        $.ajax(obj)
    },
    post: function (_url, _param, _callback) {
       _url += (_url.indexOf("?") > 0 ? "&" : "?") + "t=" + (new Date()).getTime();
        this.ajaxRequest({
            url: _url,
            data:_param,
            dataType:'json',
            type:'post'
        }, _callback);
    },
    get:function (_url, _param, _callback) {
        _url += (_url.indexOf("?") > 0 ? "&" : "?") + "t=" + (new Date()).getTime();
        this.ajaxRequest({
            url: _url,
            data:_param,
            dataType:'json',
            type:'get'
        }, _callback);
    },
    load: function(_selector, _url, _param, _callback) {
        ajax.get(_url, _param, function (_result) {
            $(_selector).html(_result);
            runCallback(_callback);
        });
    }
}

function runCallback(_callback, _result) {
    if (_callback) {
        try {
            if (typeof _callback == 'function') {
                _callback(_result);
            } else {
                if (_result) {
                    eval(_callback + "(_result)");
                } else {
                    eval(_callback + "()");
                }

            }
        } catch (e) {
            alertMessage.error(e);
        }
    }
}

var alertMessage = {
    closeLoading: function () {
        $('#loading-box').remove();
    },
    loading: function () {
        $('body').append(setting.loadingHtml);
    },
    success: function (_content) {
        if (_content) {
            $('div#success-message').fadeIn().find('span').text(_content);
        } else {
            $('div#success-message').fadeIn();
        }
        setTimeout(function () {
            $('div#success-message').fadeOut("slow");
        }, 3000);
    },
    error: function (_content, _callback) {
        $.alert({
            title: '哎呀，出错了！',
            backgroundDismiss: true,
            icon: 'fa fa-warning',
            animation: 'top',
            closeAnimation: 'bottom',
            animateFromElement: false,
            closeIcon: true,
            type: 'red',
            theme: 'bootstrap',
            content: _content || '请求遇到错误',
            buttons: {
                cancel: {
                    text: '朕知道了',
                    btnClass: 'btn-danger',
                    action: function () {
                        runCallback(_callback)
                    }
                }
            }
        })
    },
    info: function (_content,_options, _callback) {
        var _defaults = {
            backgroundDismiss: true,
                title: '消息提示',
                icon: 'fa fa-question-circle-o',
                animation: 'scaleY',
                closeAnimation: 'scaleX',
                animateFromElement: false,
                closeIcon: true,
                type: 'green',
                theme: 'bootstrap',
                content: _content,
                //autoClose: 'btnOk|5000' ,
                buttons: {
                btnOk: {
                    text: '确定',
                        btnClass: 'btn-info',
                        keys: ['enter'],
                        action: function () {
                            runCallback(_callback)
                    }
                }
            }
        }
        var _option = $.extend(_defaults, _options);
        $.alert(_option)
    },
    confirm: function (_content, _options) {
        var defaults = {
            backgroundDismiss: true,
            title: '消息提示',
            icon: 'fa fa-question-circle-o',
            animation: 'top',
            closeAnimation: 'bottom',
            animateFromElement: false,
            closeIcon: true,
            type: 'blue',
            theme: 'bootstrap',
            content: _content,
            //autoClose: 'btnOk|5000' ,
            buttons: {
                btnOk: {
                    text: '确定',
                    btnClass: 'btn-info',
                    keys: ['enter'],
                    action: function () {

                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'btn-danger'
                }
            }
        }
        var _option = $.extend(defaults, _options);
        $.confirm(_option);
    },
    autoConfirm: function (_content, _confirmBtnName, _callback) {
        _confirmBtnName = _confirmBtnName || '确定';
        var defaults = {
            backgroundDismiss: true,
            title: '消息提示',
            icon: 'fa fa-question-circle-o',
            animation: 'top',
            closeAnimation: 'bottom',
            animateFromElement: false,
            closeIcon: true,
            type: 'blue',
            theme: 'bootstrap',
            content: _content,
            autoClose: 'btnOk|5000',
            buttons: {
                btnOk: {
                    text: _confirmBtnName,
                    btnClass: 'btn-info',
                    keys: ['enter'],
                    action: function () {
                        runCallback(_callback);
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'btn-danger'
                }
            }
        }
        $.confirm(defaults);
    },
    warning: function (_content, _options) {
        var defaults = {
            title: '温馨提醒',
            backgroundDismiss: false,
            icon: 'fa fa-exclamation-triangle',
            animation: 'left',
            closeAnimation: 'right',
            animateFromElement: false,
            closeIcon: true,
            type: 'orange',
            theme: 'bootstrap',
            content: _content,
            buttons: {
                cancel: {
                    text: '朕知道了',
                    btnClass: 'btn-danger'
                }
            }
        };
        var _option = $.extend(defaults, _options);
        $.confirm(_option);
    },
    ajaxDialog: function (_utl,_param,_options) {
        var defaults = {
            title: '温馨提醒',
            backgroundDismiss: false,
            icon: 'fa fa-exclamation-triangle',
            animation: 'left',
            closeAnimation: 'right',
            animateFromElement: false,
            closeIcon: true,
            type: 'orange',
            theme: 'bootstrap',
            columnClass: 'col-xs-12',
            buttons: {
                cancel: {
                    text: '朕知道了',
                    btnClass: 'btn-danger'
                }
            },
            content: function () {
                var self = this;
                return ajax.get(_utl, _param, function (_result) {
                    alert(_result)
                    self.setContent(_result);
                })
            }
        };
        var _option = $.extend(defaults, _options);
        $.confirm(_option);
    }
}
