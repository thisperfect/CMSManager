<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017\7\26 0026
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String servicePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Cloud Admin | Error 500</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
    <!-- STYLESHEETS -->
    <!--[if lt IE 9]>
    <script src="<%=servicePath %>/plugins/js/flot/excanvas.min.js"></script>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=servicePath %>/plugins/css/cloud-admin.css" >
    <link rel="stylesheet" type="text/css"  href="<%=servicePath %>/plugins/css/responsive.css" >

    <link href="<%=servicePath %>/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- DATE RANGE PICKER -->
    <link rel="stylesheet" type="text/css" href="<%=servicePath %>/plugins/js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
</head>
<body>
<!-- PAGE -->
<section id="page">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="divide-100"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 not-found text-center">
                <div class="error-500">
                    500
                </div>
            </div>
            <div class="col-md-4 col-md-offset-4 not-found text-center">
                <div class="content">
                    <h3>Oops! Something went wrong</h3>
                    <p>
                        Don't worry! We're working on it.
                    </p>
                    <p><a href="javascript:;" onclick="showErrorBody()">
                        Show Error Message
                    </a></p>
                    <div class="btn-group">
                        <a href="javaScript:;"onclick="history.go(-1)" class="btn btn-danger"><i class="fa fa-chevron-left"></i> Go Back</a>
                        <a href="index.html" class="btn btn-default">Dashboard</a>
                    </div>
                    <div class="divide-10"></div>
                    <div style="display: none" id="error-body">
                        <p><a href="javascript:;" onclick="closeErrorBody()">
                           Close Error Body
                        </a></p>
                       <p>${error }</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--/PAGE -->
<!-- JAVASCRIPTS -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- JQUERY -->
<script src="<%=servicePath %>/plugins/js/jquery/jquery-2.0.3.min.js"></script>
<!-- JQUERY UI-->
<script src="<%=servicePath %>/plugins/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<!-- BOOTSTRAP -->
<script src="<%=servicePath %>/plugins/bootstrap-dist/js/bootstrap.min.js"></script>


<!-- DATE RANGE PICKER -->
<script src="<%=servicePath %>/plugins/js/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="<%=servicePath %>/plugins/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script src="<%=servicePath %>/plugins/js/bootstrap-daterangepicker/daterangepicker.min.js"></script>
<!-- CUSTOM SCRIPT -->
<script src="<%=servicePath %>/plugins/js/script.js"></script>
<script>
    jQuery(document).ready(function() {
        App.setPage("widgets_box");  //Set current page
        App.init(); //Initialise plugins and elements
    });
    function showErrorBody() {
       $("#error-body").show(1000);
    }
    function closeErrorBody() {
        $("#error-body").hide(1000);
    }
</script>
<!-- /JAVASCRIPTS -->
</body>
</html>