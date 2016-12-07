<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="GBK">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Login</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/css/style.css" rel="stylesheet">

  </head>
  <body>

    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
					<div class="page-header">
						<h1>
							家庭账目管理系统
						</h1>
					</div>
				</div>
				<div class="col-md-4">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
					<form class="form-horizontal"  action="<%=path%>/LoginPost.jsp" method="post" target="childIframe">
						<div class="form-group">
							 
							<label for="inputUsername3" class="col-sm-2 control-label">
								Username
							</label>
							<div class="col-sm-10">
								<input class="form-control" id="username" name="username" type="text">
							</div>
						</div>
						<div class="form-group">
							 
							<label for="inputPassword3" class="col-sm-2 control-label">
								Password
							</label>
							<div class="col-sm-10">
								<input class="form-control" id="password" name="password" type="password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									 
									<label>
										<input type="checkbox"> Remember me
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								 
								<button type="submit" class="btn btn-default">
									Sign in
								</button>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>
</div>

    <iframe style="display: none;" name="childIframe" id="childIframe"></iframe>
    <script src="<%=path%>/js/jquery.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/scripts.js"></script>
  </body>
</html>