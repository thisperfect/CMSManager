<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-9-16
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin | Login</title>
    <link href="https://cdn.bootcss.com/pace/1.0.2/themes/silver/pace-theme-flat-top.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/pace/1.0.2/pace.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/css/main.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../static/css/login.css">
    <link rel="stylesheet" href="../plugins/particles/css/style.css">
    <%@ include file="../share/lib.jsp" %>
</head>
<body>
<section id="page">
    <!-- LOGIN -->
    <section id="login" class="visible">
        <div class="content-box-plain login-box">
            <h3 class="text-center"><img src="../static/images/logo/logo.png" alt="logo"></h3>
            <div class="divide-40"></div>
            <form role="form" id="login-form">
                <input name="sign" type="hidden"/>
                <input name="timestamp" type="hidden"/>
                <div class="form-group">
                    <i class="fa fa-user"></i>
                    <input type="text" class="form-control" placeholder="登入账户名" name="username" autocomplete="off">
                </div>
                <div class="form-group">
                    <i class="fa fa-lock"></i>
                    <input type="password" class="form-control" placeholder="登入密码" name="password" autocomplete="off">
                </div>
                <div class="code_login"></div>
                <div class="checkbox">
                    <label> <input type="checkbox" name="rememberMe"> 记住我</label>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-block" id="btnSignIn" autocomplete="off" loading-text="正在登入...">登 入</button>
                </div>
            </form>
            <div class="login-helpers">
                <a href="#" onclick="swapScreen('forgot');return false;">忘记密码?</a> <br>
                还没有我们的账号? <a href="#" onclick="swapScreen('register');return false;">现在就注册!</a>
            </div>
        </div>
    </section>
    <!--/LOGIN -->
    <!-- REGISTER -->
    <section id="register" class="hide">
        <div class="content-box-plain register-box">
            <h3 class="text-center"><img src="../static/images/logo/logo.png" alt="logo"></h3>
            <div class="divide-40"></div>
            <form role="form" id="register-form">
                <input name="sign" type="hidden"/>
                <input name="timestamp" type="hidden"/>
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
                <div class="code_register form-group"></div>
                <div class="checkbox">
                    <label class=""> <input type="checkbox" class="uniform" id="clause"> 我同意 <a href="javaScript:;">服务条款</a> 和 <a href="javaScript:;">隐私政策</a></label>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-success btn-block" loading-text="注册中..." id="btnRegister">注 册</button>
                </div>
            </form>
            <div class="login-helpers">
                <a href="#" onclick="swapScreen('login');return false;"> 跳转到登入</a> <br>
            </div>
        </div>
    </section>
    <!--/REGISTER -->
    <!-- FORGOT PASSWORD -->
    <section id="forgot" class="hide">
        <div class="content-box-plain forgot-box">
            <h3 class="text-center"><img src="../static/images/logo/logo.png" alt="logo"></h3>
            <div class="divide-40"></div>
            <form role="form">
                <div class="form-group">
                    <i class="fa fa-envelope"></i>
                    <input type="email" class="form-control" placeholder="您注册时候的邮箱地址">
                </div>
                <div class="code_forgot form-group"></div>
                <div class="form-group">
                    <button type="submit" class="btn btn-info btn-block" id="btnForgot" loading-text="正在发送指令...">发送重置命令给我</button>
                </div>
            </form>
            <div class="login-helpers">
                <a href="#" onclick="swapScreen('login');return false;">跳转到登入页面</a> <br>
            </div>
        </div>
    </section>
    <!-- FORGOT PASSWORD -->
</section>
<div id="particles-js"></div>
<script src="https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${basePath }/static/js/md5.min.js"></script>
<script src="${basePath }/static/js/login.js"></script>
<script type="text/javascript" src="${basePath }/static/js/jquery.cookie.min.js"></script>
<script src="https://cdn.bootcss.com/particles.js/2.0.0/particles.min.js"></script>
<script src="${basePath }/plugins/particles/app.js"></script>
<script type="text/javascript">
    $(function () {
        var id = window.location.hash;
        if (id && id.indexOf('login,forgot,register') >= 0) {
            id = id.substring(1, id.length);
            createCode(id)
            swapScreen(id);
        } else {
            swapScreen("login");
        }

        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                $("section.visible").find('button').click();
            }
        });
        $("#login-form input[name=username]").focus();
        $("#btnSignIn").click(function () {
            var $select = $("#login");
            var _username = $("#login-form input[name=username]").val();
            if (_username === null || _username === "" || _username === undefined) {
                createErrorMessage($select, '请输入账户!');
                return false;
            } else if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(_username)) {
                createErrorMessage($select, '用户名不能有特殊字符!');
                return false;
            } else if (/(^\_)|(\__)|(\_+$)/.test(_username)) {
                createErrorMessage($select, '用户名首尾不能出现下划线\'_\'!');
                return false;
            } else if (/^\d+\d+\d$/.test(_username)) {
                createErrorMessage($select, '用户名不能全为数字!');
                return false;
            }

            var $vali = $("#login").find(".code_login");
            if ($vali.html()) {
                var _inputCode = $vali.find("input[name=validationCode]").val();
                if (!_inputCode) {
                    createErrorMessage($select, '请填写验证码!');
                    return false;
                }
            }

            var $password = $("#login-form input[name=password]");
            var _password = $password.val();
            if (!_password) {
                createErrorMessage($select, '请输入登入密码!');
                return false;
            } else if (!new RegExp("^[A-Za-z0-9]{6,12}$").test(_password)) {
                createErrorMessage($select, '密码必须6到12位，且不能出现空格!');
                return false;
            } else {
                $password.val(md5(_username + _password));
            }

            $("#login-form input[name=timestamp]").val("");
            $("#login-form input[name=sign]").val("");
            var timestamp = new Date().getTime();
            $("#login-form input[name=timestamp]").val(timestamp);
            $("#login-form input[name=sign]").val(md5(_username + $password.val() + "${key}" + timestamp));
            var $btn = $(this);
            btnLoading.loading($btn);
            $.ajax({
                type: "POST",
                url: "../system/login.shtml",
                data: $('#login-form').serialize(),
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    btnLoading.reset($btn);
                    createErrorMessage($select, XMLHttpRequest.status);
                },
                success: function (data) {
                    if (data.code === '10000') {
                        location.href = '${path }/user/index.shtml';
                    } else if (data.code === '20004') {
                        btnLoading.reset($btn);
                        createValidationCode('login', 'code_login');
                        createErrorMessage($select, data.msg);
                    } else {
                        btnLoading.reset($btn);
                        createErrorMessage($select, data.msg);
                    }
                }
            });
            $password.val('').focus();
            return false;
        });
        $("#btnRegister").click(function () {
            var $select = $("#register");

            if (!$("#clause").is(':checked')) {
                createErrorMessage($select, '请先仔细阅读相关条款!');
                return false;
            }

            var _username = $select.find("input[name=username]").val();
            if (_username === null || _username === "" || _username === undefined) {
                createErrorMessage($select, '请填写您要注册的登入账户!');
                return false;
            } else if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(_username)) {
                createErrorMessage($select, '注册的登入账户不能有特殊字符!');
                return false;
            } else if (/(^\_)|(\__)|(\_+$)/.test(_username)) {
                createErrorMessage($select, '注册的登入账户首尾不能出现下划线\'_\'!');
                return false;
            } else if (/^\d+\d+\d$/.test(_username)) {
                createErrorMessage($select, '注册的登入账户不能全为数字!');
                return false;
            }

            var _email = $select.find("input[name=email]").val();
            if (!_email) {
                createErrorMessage($select, '电子邮件不能为空!');
                return false;
            } else if (!new RegExp("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$").test(_email)) {
                createErrorMessage($select, '请输入有效的电子邮件!');
                return false;
            }

            var $password = $select.find("input[name=password]");
            var _password = $password.val();
            if (!_password) {
                createErrorMessage($select, '请输入登入密码!');
                return false;
            } else if (!new RegExp("^[A-Za-z0-9]{6,12}$").test(_password)) {
                createErrorMessage($select, '密码必须6到12位，由字母和数字组成!');
                return false;
            }
            var $repeatPassword = $select.find("input[name=repeatPassword]");
            var _repeatPassword = $repeatPassword.val();
            if (!_repeatPassword) {
                createErrorMessage($select, '请输入确认密码!');
                return false;
            }
            _repeatPassword = $repeatPassword.val();
            _password = $password.val();
            if (_repeatPassword !== _password) {
                createErrorMessage($select, '两次密码不一致!');
                return false;
            }
            var $vali = $("#register").find(".code_register");
            if ($vali.html()) {
                var _inputCode = $vali.find("input[name=validationCode]").val();
                if (!_inputCode) {
                    createErrorMessage($select, '请填写验证码!');
                    return false;
                }
            }
            var $btn = $(this);
            btnLoading.loading($btn);
            var exists = false;
            $.ajax({
                url: "${basePath}/system/checkAccount.shtml",
                data: {username: _username, email: _email},
                type: "POST",
                dataType: "JSON",
                async: false,
                success: function (result) {
                    console.log(result);
                    if (result.code != '10000') {
                        btnLoading.reset($btn);
                        createErrorMessage($select, result.msg);
                        exists = true;
                    }
                },
                error: function (XMLHttpRequest, t, e) {
                    btnLoading.reset($btn);
                    createErrorMessage($select, t);
                    exists = true;
                }
            });
            if (exists) {
                return false;
            }
            var _md5password = md5(_username + _password);
            $password.val(_md5password);
            $repeatPassword.val(_md5password);
            $("#register-form input[name=timestamp]").val("");
            $("#register-form input[name=sign]").val("");
            var timestamp = new Date().getTime();
            $("#register-form input[name=timestamp]").val(timestamp);
            $("#register-form input[name=sign]").val(md5(_username + $password.val() + "${key}" + timestamp));
            $.ajax({
                type: "POST",
                url: "${path }/system/register.shtml",
                data: $('#register-form').serialize(),
                async: true,
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    btnLoading.reset($btn);
                    $password.val(_password);
                    $repeatPassword.val(_password);
                    createErrorMessage($select, textStatus + '<br/>' + errorThrown);
                },
                success: function (data) {
                    if (data.code === '10000') {
                        location.replace('${path }/system/sendSuccess.shtml?email=' + _email);
                    } else {
                        if (data.code === '20004') {
                            createValidationCode('register', 'code_register');
                        }
                        btnLoading.reset($btn);
                        $password.val(_password);
                        $repeatPassword.val(_password);
                        createErrorMessage($select, data.msg);
                    }
                }
            });

        });
    });


    function createValidationCode(id, clas) {
        if ($("#" + id).find("div.pull-left").length > 0) {
            changeCode($("#" + id).find("div > img"));
        } else {
            var _html = '<img class="pull-right" src="${basePath }/system/createValidationCode.shtml" title="点我更换图片" onclick="changeCode(this)"/><div style="width: 260px;margin-bottom: 15px;">';
            _html += '<i class="fa fa-key"></i><input type="text" class="form-control" placeholder="验证码" name="validationCode" ></div>';
            $("#" + id + " ." + clas + "").append(_html);
        }
    }

    function changeCode(t) {
        $(t).attr('src', '${basePath }/system/createValidationCode.shtml?t=' + new Date().getTime());
    }
</script>
</body>
</html>
