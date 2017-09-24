<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE>
<html>
<head>

<title>邮件发送成功</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	<style>
		.main {
			margin-top: 150px;
			margin-bottom: 50px;
		}
		.nav > li > a:hover, .nav > li > a:focus {
			text-decoration: none;
			background:#000;
			opacity:0.1;
			border-radius: 4px;
			filter:alpha(opacity=50);
			color: #FFF;
		}
		.text-right {
			font-size: 36px;
			font-weight: lighter;
			color: #666;
		}
		.success_title {
			font-size: 16px;
			color: #666;
		}
		.success_title span {
			font-size: 16px;
			color: #00aeff;
		}
		.success_desc {
			font-size: 14px;
			color: #999;
			padding: 10px 0 36px;
		}
		.btn-info{
			width: 214px;
			height: 42px;
			background: #00a4f1;
		}
		.success_border {
			border-top: 1px solid #e7e7e7;
			margin: 40px 0 36px;
		}
		.success_fail_title {
			font-size: 14px;
			color: #666;
			font-weight: bold;
		}
		.success_fail_desc {
			font-size: 12px;
			line-height: 20px;
			color: #999;
			padding-top: 4px;
		}
		.success_fail_desc a {
			color: #00aeff;
			text-decoration: underline;
		}
		@media screen and (max-width: 768px){
			.text-right {
				text-align: center;
				margin-bottom: 50px;
			}
			p {
				text-align: center;
			}
		}
		@media screen and (max-width: 370px){
			.text-right {
				font-size: 30px;

			}
		}
	</style>

</head>

<body>
<div class="container-fluid">
	<nav class="navbar" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/CMSManager/index.html">
					<img src="../static/images/logo/logo-alt.png" height="40" alt="logo name" />
				</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li ><a href="../system/login.shtml">登 入</a></li>
					<li ><a href="../system/login.shtml#register">注 册</a></li>
				</ul>
			</div>
		</div>
	</nav>
<div class="row clearfix main">
		<div class="col-md-5 col-sm-6 col-xs-12 col-lg-5 column">
			<h1 class="text-right">验证邮箱，完成注册</h1>
		</div>
		<div class="col-md-5 col-sm-6 col-xs-12 col-lg-5 column">
			<p class="success_title"> 我们已将邮件发送至邮箱：<span id="email">${email }</span></p>
			<p class="success_desc">点击邮件内的链接，即可完成注册。</p>
			<p><a href="http://mail.163.com" target="_blank" id="blankMail">
				<button type="button" class="btn btn-info" id="signInEmail">登入邮箱验证</button></a>
			</p>
			<p class="success_border"></p>
			<p class="success_fail_title">没有收到邮件怎么办？</p>
			<p class="success_fail_desc"> 邮箱填写错误？
				<a target="_blank"  href="../system/login.shtml#register">换个邮箱</a>。
				看看是否在邮箱的垃圾目录，广告邮件目录里。稍等几分钟，若还未收到验证邮件，
				<a href="javascript:;" class="btn btn-default btn-xs" id="restLink">重新发送验证邮件</a>。</p>
		</div>
	</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
<script src="../static/js/md5.min.js"></script>
<script type="text/javascript">
    var hash = {
        'qq.com': 'http://mail.qq.com',
        'gmail.com': 'http://mail.google.com',
        'sina.com': 'http://mail.sina.com.cn',
        '163.com': 'http://mail.163.com',
        '126.com': 'http://mail.126.com',
        'yeah.net': 'http://www.yeah.net/',
        'sohu.com': 'http://mail.sohu.com/',
        'tom.com': 'http://mail.tom.com/',
        'sogou.com': 'http://mail.sogou.com/',
        '139.com': 'http://mail.10086.cn/',
        'hotmail.com': 'http://www.hotmail.com',
        'live.com': 'http://login.live.com/',
        'live.cn': 'http://login.live.cn/',
        'live.com.cn': 'http://login.live.com.cn',
        '189.com': 'http://webmail16.189.cn/webmail/',
        'yahoo.com.cn': 'http://mail.cn.yahoo.com/',
        'yahoo.cn': 'http://mail.cn.yahoo.com/',
        'eyou.com': 'http://www.eyou.com/',
        '21cn.com': 'http://mail.21cn.com/',
        '188.com': 'http://www.188.com/',
        'foxmail.com': 'http://www.foxmail.com',
        'outlook.com': 'http://www.outlook.com'
    }

    $(function () {
        $("#restLink").on("click",function () {
            $(this).addClass("disabled").text('120秒后可以重新发送');;
            setTimeout(function () {
                $(this).removeClass("disabled").text('重新发送验证邮件');
            },120000);
			var timestamp = new Date().getTime();
			var email = $("#email").text();
			var sign = md5(timestamp + email);
			$.ajax({
				url:'../system/sendActivatelink.shtml',
				data:{"timestamp":timestamp,"email":email,"sign":sign},
				dataType:'json',
				type:"post",
				success:function (data) {
                    if (data.code == '10000') {
                        $.confirm({
                            content:'激活链接已发送到您的邮箱!',
                            buttons: {
                                '好的': function () { },
                                "打开邮箱" : function(){
                                    $("#signInEmail").click();
                                }
                            },
                            autoClose:'好的|5000',
                        });
                    } else {
                        $.alert({
                            title:'邮件发送失败',
                            content:data.msg,
                            autoClose:'ok|5000'
                        });
                    }
                },
				error:function () {
                    $.alert({
                        title:'抱歉',
                        content:'请求服务器失败',
                        autoClose:'ok|5000'
                    });
                }
			})
        });

        var _mail = $("#email").html().split('@')[1];
        for (var j in hash) {
            if (j == _mail) {
                $("#blankMail").attr("href", hash[_mail]);
                break;
            }
        }
    });

</script>
</html>