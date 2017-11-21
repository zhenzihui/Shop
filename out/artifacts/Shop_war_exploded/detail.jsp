<%@ page import="shop.Product" %>
<%@ page import="User.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    User user = (User)session.getAttribute("login");
    Product product = (Product)request.getAttribute("product");
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
          rel="stylesheet" type="text/css">
    <script type="application/javascript">
        function add() {
            var cartbtn=document.getElementById("cartbtn");
            var xhr = new XMLHttpRequest();
            xhr.open("post","/cart?uid=<%=user.getId()%>&pid=<%=product.getId()%>&method=add");
            xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
            xhr.send();
            xhr.onreadystatechange=function () {
                if(xhr.readyState==4&&xhr.status==200) {
                    var msg = xhr.responseText;

                    alert("加入购物车成功!");
                }
            };



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
                <div class="section">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <img src="/img?id=<%=product.getId()%>"
                                     class="img-responsive">
                            </div>
                            <div class="col-md-6">
                                <h1> <%=product.getName()%></h1>
                                <h2>价格：<%=product.getPrice()%></h2>
                                <h4>产地：<%=product.getCity()%></h4>

                                <h5>库存：<%=product.getNumber()%></h5>
                                <button id="cartbtn" class="btn-primary btn" onclick="add()"  >加入购物车</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
