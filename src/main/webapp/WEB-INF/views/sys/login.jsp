<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Cloud Admin | Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
    <%@ include file="../share/lib.jsp"%>

    <link rel="stylesheet"  href="${basePath}/plugins/particles/css/style.css">
    <link rel="stylesheet" href="${basePath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"  href="${basePath }/static/css/login/cloud-admin.min.css" >
    <%--<link rel="stylesheet" type="text/css" href="${basePath}/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/login.css">--%>
    <!-- UNIFORM -->
    <style type="text/css">
        .visible  {
            display: block;
        }
         .error-div {
            width: 100%;
             background: #ffebeb;
             color: #e4393c;
             border: 1px solid #faccc6;
          padding: 3px 10px 3px 10px;
             line-height: 15px;
             height: auto;
        }
         .form-control {
             height: 42px;
         }
         .login-box-plain, form .form-control,.btn {
            border-radius: 0px;
        }
       .login {
            background: url("${basePath }/static/images/light_noise_diagonal.png") repeat scroll 0 0 rgba(0, 0, 0, 0);
            height: 100%;
            min-height: 100%;
        }
       .form-group .validationCode,.imgCode {
            width: 50%;
        }
       .validation {
           height: 42px;
       }
         .form-group .imgCode img{
             margin-left: 10px;
             cursor:pointer;
         }
    </style>
</head>
<body class="login">
<!-- PAGE -->
<section id="page">
    <!-- LOGIN -->
    <section id="login" class="visible">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="login-box-plain">
                        <h3 class="bigintro">欢迎登入</h3>
                        <div class="divide-40"></div>
                        <form role="form" id="login-form">
                            <input name="sign" type="hidden" />
                            <input name="timestamp" type="hidden" />
                            <div class="form-group">
                                <i class="fa fa-user"></i>
                                <input type="text" class="form-control" placeholder="登入账户名" name="username" autocomplete="off">
                            </div>
                            <div class="form-group">
                                <i class="fa fa-lock"></i>
                                <input type="password"  class="form-control" placeholder="登入密码" name="password" autocomplete="off">
                            </div>
                            <div class="code_login"></div>
                            <div class="form-actions">
                                <label class="checkbox"> <input type="checkbox" name="rememberMe" > 记住我</label>
                                <button type="button" class="btn btn-primary" id="btnSignIn" autocomplete="off" loading-text="正在登入...">登 入</button>
                            </div>
                        </form>
                        <div class="login-helpers">
                            <a href="#" onclick="swapScreen('forgot');return false;">忘记密码?</a> <br>
                            还没有我们的账号? <a href="#" onclick="swapScreen('register');return false;">现在就注册!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--/LOGIN -->
    <!-- REGISTER -->
    <section id="register" class="hide">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="login-box-plain">
                        <h2 class="bigintro">注册账户</h2>
                        <div class="divide-40"></div>
                        <form role="form" id="register-form">
                            <input name="sign" type="hidden" />
                            <input name="timestamp" type="hidden" />
                            <div class="form-group">
                                <i class="fa fa-font"></i>
                                <input type="text" class="form-control" name="name" placeholder="请填写您的姓名">
                            </div>
                            <div class="form-group">
                                <i class="fa fa-user"></i>
                                <input type="text" class="form-control" name="username" placeholder="请填写您的要注册的账户名">
                            </div>
                            <div class="form-group">
                                <i class="fa fa-envelope"></i>
                                <input type="email" class="form-control" name="email" placeholder="请填写您常用的邮箱地址">
                            </div>
                            <div class="form-group">
                                <i class="fa fa-lock"></i>
                                <input type="password" class="form-control" name="password" placeholder="请填写您的账户密码">
                            </div>
                            <div class="form-group">
                                <i class="fa fa-check-square-o"></i>
                                <input type="password" class="form-control" name="repeatPassword" placeholder="请确认您的账户密码">
                            </div>
                            <div class="code_register"></div>
                            <div class="form-actions">
                                <label class="checkbox"> <input type="checkbox"  class="uniform" id="clause"> 我同意 <a href="javaScript:;">服务条款</a> 和 <a href="javaScript:;">隐私政策</a></label>
                                <button type="button" class="btn btn-success" loading-text="注册中..." id="btnRegister">注 册</button>
                            </div>
                        </form>
                        <div class="login-helpers">
                            <a href="#" onclick="swapScreen('login');return false;"> 跳转到登入</a> <br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--/REGISTER -->
    <!-- FORGOT PASSWORD -->
    <section id="forgot" class="hide">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="login-box-plain">
                        <h2 class="bigintro">忘记密码</h2>
                        <div class="divide-40"></div>
                        <form role="form">
                            <div class="form-group">
                                <label>邮箱地址</label>
                                <i class="fa fa-envelope"></i>
                                <input type="email" class="form-control" placeholder="您注册时候的邮箱地址" >
                            </div>
                            <div class="code_forgot"></div>
                            <div class="form-actions">
                                <button type="submit"  class="btn btn-info" id="btnForgot" loading-text="正在发送指令...">发送重置命令给我</button>
                            </div>
                        </form>
                        <div class="login-helpers">
                            <a href="#" onclick="swapScreen('login');return false;">跳转到登入页面</a> <br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- FORGOT PASSWORD -->
</section>
<div id="particles-js"></div>
<!--/PAGE -->
<!-- JAVASCRIPTS -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- JQUERY -->
<script src="https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
<!-- BOOTSTRAP -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${basePath }/static/js/md5.min.js"></script>
<script src="${basePath }/static/js/login.js"></script>
<script src="https://cdn.bootcss.com/particles.js/2.0.0/particles.min.js"></script>
<script src="${basePath }/plugins/particles/app.js"></script>
<!-- UNIFORM -->
<script type="text/javascript" src="${basePath }/static/js/jquery.cookie.min.js"></script>
<!-- CUSTOM SCRIPT -->
<script type="text/javascript">
    function swapScreen(id) {
        createCode(id);
        jQuery('.visible').addClass('hide');
        jQuery('#'+id).addClass('visible');
    }
</script>

<!-- /JAVASCRIPTS -->
</body>
</html>