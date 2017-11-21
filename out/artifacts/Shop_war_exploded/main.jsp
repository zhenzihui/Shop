<%@ page import="shop.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="User.User" %>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/28
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>



  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
  <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
        rel="stylesheet" type="text/css">
  <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
        rel="stylesheet" type="text/css">
   
  <%
    String alert =(String) request.getAttribute("alert");
  if(alert!=null)
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
            <a href="/auth?method=show&uid=<%=user.getId()%>"><%=user.getName()%>（<%=user.getId()%>）</a>
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

  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="section">
          <div class="container">
            <div class="row">
              <% List<Product> products=(List<Product>)request.getAttribute("products");%>
              <% for (Product product:products){%>
              <div class="col-md-3">
                <img src="/img?id=<%=product.getId()%>" class="media-object" width="150" height="150">


                <div><span style="text-align: center"><a href="/detail?id=<%=product.getId()%>"><%=product.getName()%></a> </span></div>
              </div>


              <%}%>




            </div>
            <%--<div class="row">--%>
              <%--<div class="col-md-3">--%>
                <%--<a><img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png" class="img-responsive"></a>--%>
              <%--</div>--%>
              <%--<div class="col-md-3">--%>
                <%--<a><img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png" class="img-responsive"></a>--%>
              <%--</div>--%>
              <%--<div class="col-md-3">--%>
                <%--<a><img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png" class="img-responsive"></a>--%>
              <%--</div>--%>
              <%--<div class="col-md-3">--%>
                <%--<a><img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png" class="img-responsive"></a>--%>
              <%--</div>--%>
            <%--</div>--%>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>

</html>