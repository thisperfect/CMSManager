var setting = {
    width:$(window).width(),
    height:$(window).height(),
    'loadindHtml' :'<div id="loading"><div id="loading-center"><div id="loading-center-absolute" style="top:*gpx;left:*hpx" ><div class="object" id="first_object"></div><div class="object" id="second_object"></div><div class="object" id="third_object"></div></div></div></div>'
}

function isIEwarn() {
    var userAgent = navigator.userAgent;
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1;
  //  var isEdge = userAgent.indexOf("Edge") > -1 && !isIE;
    //var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion <= 9) {
            alertMessage.warning('您的浏览器太过古老，部分功能将不能使用。为了您更好的体验全部功能推荐下载最新的其它浏览器。',{
                buttons: {
                    cancel: {
                        text: '朕知道了',
                        btnClass: 'btn-danger'
                    },
                    firefox: {
                        text: '去下载火狐',
                        btnClass: 'btn-info',
                        action:function () {
                            window.open('https://www.mozilla.org/zh-CN/firefox/new/?scene=2','target="_blank"');
                        }
                    },
                    chrome:  {
                        text: '去下载火狐',
                        btnClass: 'btn-success',
                        action: function () {
                            window.open('http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html#eula','target="_blank"');
                        }
                    }
                }});
        } else  {
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
                var _f =  _w.f1 + ".png";
                if (new Date().getHours() > 17) {
                    _f =  _w.f2 + "b.png";
                }
                var html = '<div class="col-sm-4"> <img class="img-rounded img-responsive" src="../static/images/weather/weather_'+ _f +'"/>';
                html += ' </div><div class="col-sm-8"><h2><i class="fa fa-map-marker fa-fw h6"></i>'+citytq+'<span>'+_w.s1+'</span></h2>';
                html += '<h5>温度：' + _w.t2 + '℃～'+ _w.t1 +'℃</h5><h5>'+ _w.d1 + _w.p1 +'级</h5></div>';
                $(select).html(html);
            },error :function () {
                $('#weather').html('<P class="text-muted text-center">获取天气信息失败......</P>');
            }
        });
    });
}


function ajaxLoad(_selector,_url,_param,_callback) {
    _url += (_url.indexOf("?") > 0 ? "&" : "?") + "t=" + (new Date()).getTime();
    $.post(_url, _param, function(_result){
        if (_result['code'] != '20000') {
            alertMessage.error(_result['msg']);
        } else {
            $(_selector).html(_result);
            runCallback(_callback);
        }
    });
}

function runCallback(_callback,_result) {
    if (_callback) {
        try {
            if (typeof _callback == 'function') {
                _callback(_result);
            } else {
                if (_result) {
                    eval(_callback+"(_result)");
                } else {
                    eval(_callback+"()");
                }

            }
        } catch(e) {
            alertMessage.error(e);
        }
    }
}

var alertMessage = {
    closeLoading: function () {
        $('#loading').remove();
    },
    loading: function () {
        $('body').append(setting.loadindHtml.replace('*h',setting.width / 2 - 35).replace('*g', setting.height / 2 - 35));
    },
    success: function (_content) {
        if (_content) {
            $('div#success-message').fadeIn().find('span').text(_content);
        } else {
            $('div#success-message').fadeIn();
        }
        setTimeout(function () {
            $('div#success-message').fadeOut("slow");
        },3000);
    },
    error: function(_content,_callback){
        $.alert({
            title: '哎呀，出错了！',
            backgroundDismiss:true,
            icon: 'fa fa-warning',
            animation: 'top',
            closeAnimation: 'bottom',
            animateFromElement: false,
            closeIcon: true,
            type:'red',
            theme:'bootstrap',
            content: _content,
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
    info: function (_content, _callback) {
        $.alert({
            backgroundDismiss:true,
            title:false,
            type: 'green',
            animation: 'scaleY',
            closeAnimation: 'scaleX',
            theme:'bootstrap',
            animateFromElement: false,
            content: _content,
            buttons: {
                cancel: {
                    text: '朕知道了',
                    btnClass: 'btn-info',
                    action: function () {
                        runCallback(_callback)
                    }
                }
            }
        })
    },
    confirm: function (_content,_options) {
        var defaults =   {
            backgroundDismiss:true,
            title: '消息提示',
            icon: 'fa fa-question-circle-o',
            animation: 'top',
            closeAnimation: 'bottom',
            animateFromElement: false,
            closeIcon: true,
            type:'blue',
            theme:'bootstrap',
            content: _content,
            //autoClose: 'btnOk|5000' ,
            buttons: {
                btnOk: {
                    text:  '确定',
                    btnClass: 'btn-info',
                    keys: ['enter'],
                    action:function () {

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
    autoConfirm:function (_content, _confirmBtnName, _callback) {
        _confirmBtnName = _confirmBtnName || '确定';
        var defaults =   {
            backgroundDismiss:true,
            title: '消息提示',
            icon: 'fa fa-question-circle-o',
            animation: 'top',
            closeAnimation: 'bottom',
            animateFromElement: false,
            closeIcon: true,
            type:'blue',
            theme:'bootstrap',
            content: _content,
            autoClose: 'btnOk|5000' ,
            buttons: {
                btnOk: {
                    text:  _confirmBtnName ,
                    btnClass: 'btn-info',
                    keys: ['enter'],
                    action:function () {
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
            backgroundDismiss:false,
            icon: 'fa fa-exclamation-triangle',
            animation: 'left',
            closeAnimation: 'right',
            animateFromElement: false,
            closeIcon: true,
            type:'orange',
            theme:'bootstrap',
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
    }
}
