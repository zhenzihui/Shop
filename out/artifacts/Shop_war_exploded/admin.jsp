<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/28
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GB2312" language="java" %>
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
                <a class="navbar-brand" href="#"><span>在线化妆品店</span></a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active">
                        <a href="/">主页</a>
                    </li>
                    <li>
                        <a href="/admin.jsp">上传</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="cover-image"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form role="form" action="/admin" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label" for="exampleInputEmail1">品名：</label>
                        <input class="form-control" name="name" id="exampleInputEmail1" type="text">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="exampleInputPassword1">产地：</label>
                        <input class="form-control" name="city" id="exampleInputPassword1" type="text">
                    </div>
                    <div class="form-group">
                        <label class="control-label">定价：</label>
                        <input class="form-control" name="price" type="text">
                    </div>
                    <div class="form-group">
                        <label class="control-label">库存：</label>
                        <input class="form-control" name="number" type="number">
                    </div>
                    <div class="form-group">
                        <label class="control-label">图片</label>
                        <input type="file" name="img">
                    </div>
                    <button type="submit" class="btn btn-default">上传</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>
