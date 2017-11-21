<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/3
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="User.User" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User)session.getAttribute("login");
    List<Product> items =(List<Product>) request.getAttribute("items");
    List<Product> history =(List<Product>)request.getAttribute("history");
%>
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
        if((alert= (String) request.getAttribute("alert"))!=null){
    %>
    <script><%=alert%></script>
    <%}%>
<script type="application/javascript">
    function Delete(id) {
        var xhr = new XMLHttpRequest();
        xhr.open("post","/cart?method=rm&pid="+id+"&uid="+<%=user.getId()%>);
        xhr.setRequestHeader("content-type","application/www-form-urlencoded");

        xhr.send();
        xhr.onreadystatechange=function () {
            if(xhr.status==200&&xhr.readyState==4)
            {

                var product = document.getElementById("p"+id);
                product.parentNode.removeChild(product);

            }

        }

    }
</script>
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
    <div class="cover-image"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div>
                    <h3>购物车</h3>
                </div>
                <%try{

                %>
                <ul class="media-list">

                    <%for (Product product:items){%>

                        <li class="media" id="p<%=product.getId()%>">
                            <a class="pull-left"><img class="media-object" src="/img?id=<%=product.getId()%>"
                                                      height="200" width="200"></a>
                            <div class="media-body">
                                <a href='/detail?id=<%=product.getId()%>'> <h4 class="media-heading"><%=product.getName()%>
                                </h4></a>
                                <h3>产地：<%=product.getCity()%>
                                </h3>
                                <h2>价格：<%=product.getPrice()%>
                                </h2>
                                <button class="btn-danger btn" id="<%=product.getId()%>" onclick="Delete(<%=product.getId()%>)">删除</button>
                            </div>
                        </li>

                    <%}%>

                </ul><%}catch (Exception e)
            {

            }%>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section">
                    <div class="container">
                        <div class="row">
                            <div><h3>浏览历史</h3></div>
                            <%
                                try{

                                for(Product pro:history){%>
                            <div class="col-md-3">
                                <a href="/detail?id=<%=pro.getId()%>"><img src="/img?id=<%=pro.getId()%>" class="img-object" width="65" height="65"><div><label><%=pro.getName()%></label></div></a>
                            </div>
                            <%}}catch (Exception e){e.printStackTrace();}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
