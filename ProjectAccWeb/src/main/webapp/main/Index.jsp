<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqx.acc.util.CommonUtils" %>
<!-- < % @ include file="../sessionCheck.jsp" % > -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String nowstr = CommonUtils.getNowDate();
String lastnowstr = CommonUtils.getLastNowDate();
String username = (String)session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="GBK">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>ACC</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/js/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
    <link href="<%=path%>/css/style.css" rel="stylesheet">
    <style type="text/css">
    	body footer {
		    background-color: #e5e5e5;
		    border-top: 1px solid #ccc;
		    margin-top: 5px;
		    padding: 5px 0 5px;
		}
    </style>
  </head>
  <body>
	<!-- 遮罩层 -->
	<div class="modal fade" id="loadingModal">
		<div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
			<div class="progress progress-striped active" style="margin-bottom: 0;">
				<div class="progress-bar" style="width: 100%;"></div>
			</div>
			<h5>正在加载...</h5>
		</div>
	</div>
	<!-- 主体 -->
    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default navbar-static-top" role="navigation">
				<div class="navbar-header">
					 
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="javascript:void(0);">Acc</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">账单<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a id="modal-advanced-query" href="#modal-container-advanced-query" data-toggle="modal">高级查询</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a id="modal-import" href="#modal-container-import" data-toggle="modal">导入</a>
								</li>
								<li>
									<a id="modal-export" href="#modal-container-export" data-toggle="modal">导出</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a id="modal-add" href="#modal-container-add" role="button" class="btn" data-toggle="modal">新增</a>
						</li>
						<li class="dropdown">
							<a id="modal-type" href="#modal-container-type" role="button" class="btn" data-toggle="modal">分类</a>
						</li>
						<li class="dropdown">
							<a id="modal-my-card" href="#modal-container-my-card" role="button" class="btn" data-toggle="modal">我的卡</a>
						</li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input id="fastlist_value" class="form-control" type="text">
						</div> 
						<button id="fastlist_query_btn" class="btn btn-default">
							快速查询
						</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="#" id="logout_btn">登出</a>
						</li>
					</ul>
				</div>
				
			</nav>
			<table class="table table-bordered table-hover table-condensed" id="query_table">
				<thead>
					<tr>
						<th>类型</th>
						<th>值</th>
						<th>说明</th>
						<th>分类</th>
						<th>我的卡</th>
						<th>时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			
			<div class="row">
				<div class="col-md-4">
				</div>
				<div id="listpagediv" class="col-md-4">
					<ul class="pagination">
						<li>
							<a href="#">Prev</a>
						</li>
						<li>
							<a href="#">1</a>
						</li>
						<li>
							<a href="#">2</a>
						</li>
						<li>
							<a href="#">3</a>
						</li>
						<li>
							<a href="#">4</a>
						</li>
						<li>
							<a href="#">5</a>
						</li>
						<li>
							<a href="#">Next</a>
						</li>
					</ul>
				</div>
				<div class="col-md-4">
				</div>
			</div>
			
			<footer>
				<div class="container">
					<p><span>@ Family account management system</span></p>
				</div>	
			</footer>
		</div>
	</div>
</div>

<!-- 高级查询 -->
<div class="modal fade" id="modal-container-advanced-query" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				 
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					高级查询
				</h4>
			</div>
			<div class="modal-body">
				...
			</div>
			<div class="modal-footer">
				 
				<button type="button" class="btn btn-default" data-dismiss="modal">
					Close
				</button> 
				<button type="button" class="btn btn-primary">
					Save changes
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 新增 -->
<div class="modal fade" id="modal-container-add" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">				 
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					新增
				</h4>
			</div>
			<div class="modal-body">				
				<div class="row">
					<div class="col-md-4">
	                    <select id="acc_select" class="form-control">
	                    	<option selected value="1">收入</option>
	                    	<option value="2">支出</option>
	                    	<option value="3">透支</option>
	                	</select>
					</div>
					<div class="col-md-4">
	                    <select id="acc_use_type_select" class="form-control">
							<option selected value="网购">网购</option>
							<option value="团购">团购</option>
	                	</select>
					</div>
					<div class="col-md-4">
	                    <select id="acc_my_card_select" class="form-control">
							<option selected value="现金">现金</option>
							<option value="银行卡">银行卡</option>
							<option value="支付宝">支付宝</option>
	                	</select>
					</div>
				</div>				
				<div class="row">
					<div class="col-md-12">
						<div class="row">
							<br>
							<div class="col-md-12">
								<label for="inputUsername1" class="col-sm-2 control-label">值</label>
								<div class="col-sm-10"><input class="form-control" id="accvalue" name="accvalue" type="text"></div>
							</div>
						</div>
						<div class="row">
							<br>
							<div class="col-md-12">
								<label for="inputUsername2" class="col-sm-2 control-label">说明</label>
								<div class="col-sm-10"><input class="form-control" id="accdesc" name="accdesc" type="text"></div>
							</div>
						</div>
						<div class="row">
							<br>
							<div class="col-md-12">
								<label for="inputUsername3" class="col-sm-2 control-label">时间</label>
								<div class="col-sm-10">
									<input class="form-control" id="acctime" name="acctime" type="text" readonly>
									<input style="display:none" id="accseqid">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">				 
				<button type="button" class="btn btn-primary" data-dismiss="modal"  id="add_newbtn">
					增加
				</button> 
				<button type="button" class="btn btn-default" data-dismiss="modal">
					取消
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 导入 -->
<div class="modal fade" id="modal-container-import" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				 
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					导入
				</h4>
			</div>
			<div class="modal-body">
				...
			</div>
			<div class="modal-footer">
				 
				<button type="button" class="btn btn-default" data-dismiss="modal">
					Close
				</button> 
				<button type="button" class="btn btn-primary">
					Save changes
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 导出 -->
<div class="modal fade" id="modal-container-export" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				 
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					导出
				</h4>
			</div>
			<div class="modal-body">
				...
			</div>
			<div class="modal-footer">
				 
				<button type="button" class="btn btn-default" data-dismiss="modal">
					Close
				</button> 
				<button type="button" class="btn btn-primary">
					Save changes
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 分类 -->
<div class="modal fade" id="modal-container-type" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">				 
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					分类
				</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div id="div_modal_type_query" class="row">
						<div class="col-md-12">
							<form class="navbar-form navbar-left" role="search">
								<div class="form-group">
									<input id="fastusetype_value" class="form-control" type="text">
								</div> 
								<button id="fastusetype_query_btn" class="btn btn-default">
									快速查询
								</button>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<table id="modal_type_table" class="table">
								<thead>
									<tr>
										<th>名称</th>
										<th>说明</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<div id="div_modal_type_pagination">
							<ul class="pagination">
								<li>
									<a href="#">Prev</a>
								</li>
								<li>
									<a href="#">1</a>
								</li>
								<li>
									<a href="#">2</a>
								</li>
								<li>
									<a href="#">3</a>
								</li>
								<li>
									<a href="#">4</a>
								</li>
								<li>
									<a href="#">5</a>
								</li>
								<li>
									<a href="#">Next</a>
								</li>
							</ul>
							</div>
							<div class="row">
								<div class="col-md-6">
									<label for="inputUsername1" class="col-sm-4 control-label">分类名称</label>									
									<input class="form-control" id="modal_type_name" name="modal_type_name" type="text">
								</div>
								<div class="col-md-6">
									<label for="inputUsername1" class="col-sm-4 control-label">分类说明</label>
									<input class="form-control" id="modal_type_desc" name="modal_type_desc" type="text">
									<input id="modal_type_status" style="display:none" type="text">
									<input id="modal_type_seq" style="display:none" type="text">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">				 
				<button id="modal_type_btn1" type="button" class="btn btn-primary">
					新增
				</button> 
				<button id="modal_type_btn2" type="button" class="btn btn-default" >
					取消
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 我的卡 -->
<div class="modal fade" id="modal-container-my-card" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">				 
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					我的卡
				</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div id="div_modal_mycard_query" class="row">
						<div class="col-md-12">
							<form class="navbar-form navbar-left" role="search">
								<div class="form-group">
									<input id="fastmycard_value" class="form-control" type="text">
								</div> 
								<button id="fastmycard_query_btn" class="btn btn-default">
									快速查询
								</button>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<table id="modal_mycard_table" class="table">
								<thead>
									<tr>
										<th>名称</th>
										<th>说明</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<div id="div_modal_mycard_pagination">
							<ul class="pagination">
								<li>
									<a href="#">Prev</a>
								</li>
								<li>
									<a href="#">1</a>
								</li>
								<li>
									<a href="#">2</a>
								</li>
								<li>
									<a href="#">3</a>
								</li>
								<li>
									<a href="#">4</a>
								</li>
								<li>
									<a href="#">5</a>
								</li>
								<li>
									<a href="#">Next</a>
								</li>
							</ul>
							</div>
							<div class="row">
								<div class="col-md-6">
									<label for="inputUsername1" class="col-sm-4 control-label">卡片名称</label>									
									<input class="form-control" id="modal_mycard_name" name="modal_mycard_name" type="text">
								</div>
								<div class="col-md-6">
									<label for="inputUsername1" class="col-sm-4 control-label">卡片说明</label>
									<input class="form-control" id="modal_mycard_desc" name="modal_mycard_desc" type="text">
									<input id="modal_mycard_status" style="display:none" type="text">
									<input id="modal_mycard_seq" style="display:none" type="text">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="modal_mycard_btn1" type="button" class="btn btn-primary">
					新增
				</button> 
				<button id="modal_mycard_btn2" type="button" class="btn btn-default">
					取消
				</button>
			</div>
		</div>
	</div>
</div>
    <script src="<%=path%>/js/page.js"></script>
    <script type="text/javascript">
    	var rp = "<%=path%>";
    	var nowstr = "<%=nowstr%>";
    	var lastnowstr = "<%=lastnowstr%>";
    	var listpagenum = 10;
    	var dimpagenum = 5;
    	var username = "<%=username%>";
    	// 分页对象
    	var plist = new Page("listpagediv","plist");
    	var ptypelist = new Page("div_modal_type_pagination","ptypelist");
    	var pmycardlist = new Page("div_modal_mycard_pagination","pmycardlist");
    </script>
    <script src="<%=path%>/js/jquery.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/json.js"></script>
    <script src="<%=path%>/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>
    <script src="<%=path%>/js/Index.js" charset="UTF-8"></script>
  </body>
</html>
