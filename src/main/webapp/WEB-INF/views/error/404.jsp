<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017\7\26 0026
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Cloud Admin | Error 404</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
    <!-- STYLESHEETS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/login/cloud-admin.min.css" >
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
                <div class="error">
                    404
                </div>
            </div>
            <div class="col-md-4 col-md-offset-4 not-found text-center">
                <div class="content">
                    <h3>Page not Found</h3>
                    <p>
                        Sorry, but the page you're looking for has not been found<br />
                        Try checking the URL for errors, <a href="index.html">goto home</a> or try to search below.
                    </p>
                    <%--<form action="#">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="search here...">
                            <span class="input-group-btn">
								<button type="submit" class="btn btn-success"><i class="fa fa-search"></i></button>
							</span>
                        </div>
                    </form>--%>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
