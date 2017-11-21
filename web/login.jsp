<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/2
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="User.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
          rel="stylesheet" type="text/css">

    <%
        String alert = "";
    if((alert= (String) request.getAttribute("alert"))!=null)
    %>
   <script><%=alert%></script>

</head>
<body>

<div class="cover">
    <div class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">在线化妆品店</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <%
                        User user = (User)session.getAttribute("login");
                    %>
                    <%if(user==null){ %>
                    <li class="active">
                        <a href="/login.jsp">登录</a>
                    </li>
                    <li class="active">
                        <a href="/register.jsp">注册</a>
                    </li>
                    <%}else{

                    %>
                    <li class="active">
                        <a href="#"><%=user.getName()%>（<%=user.getId()%>）</a>
                    </li>
                    <li class="active">
                        <a href="/auth?method=logout">注销</a>
                    </li>

                    <%if(user.getRole().equals("admin")){%>
                    <li>
                        <a href="/admin.jsp">上传</a>
                    </li>
                    <%}%>
                    <%}%>
                </ul>
            </div>
        </div>
    </div>
    <div class="cover-image"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal" role="form" method="post" action="/auth">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="inputEmail3" class="control-label">账号：</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" name="id" id="inputEmail3">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label for="inputPassword3" class="control-label">密码：</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" id="inputPassword3" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                       <input type="hidden" name="method" value="login">
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">登录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
