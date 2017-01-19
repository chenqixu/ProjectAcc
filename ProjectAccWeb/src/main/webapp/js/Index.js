// 初始化
$(function(){
	$("#acctime").datetimepicker({minView:'2',format: 'yyyy-mm-dd',startView:2,autoclose: true,startDate:'2016-01-01',endDate:'2099-12-31'});
    $('#modal-container-add').modal({backdrop: 'static',keyboard: false});
    $('#modal-container-add').modal('hide');
    $('#modal-container-type').modal({backdrop: 'static',keyboard: false});
    $('#modal-container-type').modal('hide');
    $('#modal-container-my-card').modal({backdrop: 'static',keyboard: false});
    $('#modal-container-my-card').modal('hide');
    // 进度条遮罩层初始化
    $("#loadingModal").modal({backdrop:'static', keyboard:false});
    /***********界面新增*************/
	// 绑定事件modal-add
	$('#modal-add').on('click',function(){
		$('#add_newbtn').html("新增");
		modaladdclear();
	});
    // 绑定modal-add窗口的新增按钮
    $('#add_newbtn').on('click',function(){
    	var baseBean = {};
    	baseBean.acc_type=jQuery("#acc_select").val();
    	baseBean.acc_use_type=jQuery("#acc_use_type_select").val();
    	baseBean.acc_card=jQuery("#acc_my_card_select").val();
    	baseBean.acc_use_time1=jQuery("#acctime").val();
    	baseBean.acc_value1=jQuery("#accvalue").val();
    	baseBean.acc_desc=jQuery("#accdesc").val();
    	baseBean.acc_sts=1;
    	baseBean.user_name=username;
		if($('#add_newbtn').html()=="新增"){
	    	var baseBeanStr = JSON.encode(baseBean);
	    	// 发送请求，增加记录
	    	$.getScript(rp+"/main/IndexPost.jsp?beanStr="+encodeURI(encodeURI(baseBeanStr))+"&action=add",function(rs){
	    	    // 查询全部详单
	    		queryAccList(1);
	    	});
		}else{
			baseBean.seq_id=$("#accseqid").val();
	    	var baseBeanStr = JSON.encode(baseBean);
	    	// 发送请求，修改记录
	    	$.getScript(rp+"/main/IndexPost.jsp?beanStr="+encodeURI(encodeURI(baseBeanStr))+"&action=modify",function(rs){
	    	    // 查询全部详单
	    		queryAccList(1);
	    	});
		}
    });
    /***********界面分类*************/
	// 绑定事件modal-type
	$('#modal-type').on('click',function(){
		$('#modal_type_btn1').html("新增");
		modaltypeclear();
		changemodaltype('init');
	});
	// 绑定modal-type窗口新增的新增按钮    		
	$('#modal_type_btn1').on('click',function(){
		if($('#modal_type_btn1').html()=="新增"){
			$('#modal_type_status').val("add");
			$('#modal_type_btn1').html("保存");
			changemodaltype('add');
		}else{
			// 保存处理
	    	var baseBean = {};
			baseBean.acc_use_name=$('#modal_type_name').val();
			baseBean.acc_use_desc=$('#modal_type_desc').val();
			baseBean.user_name=username;
			baseBean.acc_sts=1;
			if($('#modal_type_status').val()=="add"){
		    	var baseBeanStr = JSON.encode(baseBean);
				$.getScript(rp+"/main/Dimquery.jsp?qryBean=usetype&beanStr="+encodeURI(encodeURI(baseBeanStr))+"&action=add",function(rs){
				    // 查询分类
					 queryUseType(1);
					// 状态初始化
					$('#modal_type_btn1').html("新增");
					modaltypeclear();
					changemodaltype('init');
				});
			}else if($('#modal_type_status').val()=="modify"){
				baseBean.acc_use_type=$('#modal_type_seq').val();
		    	var baseBeanStr = JSON.encode(baseBean);
				$.getScript(rp+"/main/Dimquery.jsp?qryBean=usetype&beanStr="+encodeURI(encodeURI(baseBeanStr))+"&action=modify",function(rs){
				    // 查询分类
					 queryUseType(1);
					// 状态初始化
					$('#modal_type_btn1').html("新增");
					modaltypeclear();
					changemodaltype('init');
				});
			}
		}
	});
	// 绑定modal-type窗口取消的新增按钮    		
	$('#modal_type_btn2').on('click',function(){
		$('#modal_type_btn1').html("新增");
		modaltypeclear();
		changemodaltype('init');
	});
	// 绑定快速查询按钮
	$('#fastusetype_query_btn').on('click',function(){
		alert($('#fastusetype_value').val());
		return false;
	});
    /***********界面我的卡*************/		
	// 绑定事件modal-my-card
	$('#modal-my-card').on('click',function(){
		$('#modal_mycard_btn1').html("新增");
		modalmycardclear();
		changemodalmycard('init');
	});
	// 绑定modal-my-card窗口新增的新增按钮    		
	$('#modal_mycard_btn1').on('click',function(){
		if($('#modal_mycard_btn1').html()=="新增"){
			$('#modal_mycard_status').val("add");
			$('#modal_mycard_btn1').html("保存");
			changemodalmycard('add');
		}else{
			// 保存处理
	    	var baseBean = {};
			baseBean.acc_card_name=$('#modal_mycard_name').val();
			baseBean.acc_card_desc=$('#modal_mycard_desc').val();
			baseBean.user_name=username;
			baseBean.acc_sts=1;
			if($('#modal_mycard_status').val()=="add"){
		    	var baseBeanStr = JSON.encode(baseBean);
				$.getScript(rp+"/main/Dimquery.jsp?qryBean=mycard&beanStr="+encodeURI(encodeURI(baseBeanStr))+"&action=add",function(rs){
				    // 查询分类
					queryMyCard(1);
					// 状态初始化
					$('#modal_mycard_btn1').html("新增");
					modalmycardclear();
					changemodalmycard('init');
				});
			}else if($('#modal_mycard_status').val()=="modify"){
				baseBean.acc_my_card=$('#modal_mycard_seq').val();
		    	var baseBeanStr = JSON.encode(baseBean);
				$.getScript(rp+"/main/Dimquery.jsp?qryBean=mycard&beanStr="+encodeURI(encodeURI(baseBeanStr))+"&action=modify",function(rs){
				    // 查询分类
					queryMyCard(1);
					// 状态初始化
					$('#modal_mycard_btn1').html("新增");
					modalmycardclear();
					changemodalmycard('init');
				});
			}
		}
	});
	// 绑定modal-type窗口取消的新增按钮    		
	$('#modal_mycard_btn2').on('click',function(){
		$('#modal_mycard_btn1').html("新增");
		modalmycardclear();
		changemodalmycard('init');
	});
	// 绑定快速查询按钮
	$('#fastmycard_query_btn').on('click',function(){
		alert($('#fastmycard_value').val());
		return false;
	});
    /***********界面清单*************/
	// 绑定快速查询按钮
	$('#fastlist_query_btn').on('click',function(){
		alert($('#fastlist_value').val());
		return false;
	});
	/***********登出*****************/
	// 绑定登出按钮
	$('#logout_btn').on('click',function(){
		if(window.confirm('你确定要登出吗？')){
			window.location.href=rp+"/Logout.jsp";
			return true;
		}else{
			return false;
		}
	});
    // 查询全部详单
    queryAccList(1);
	// 我的分类初始化
    queryMyCard(1);
    // 类型初始化
    queryUseType(1);
});

/***********界面清单*************/
// 查询全部详单
function queryAccList(startnum){
	// 显示遮罩层
	loadModal("show");
	var v_startnum = (startnum-1)*listpagenum+1;
	var baseBean = {};
	baseBean.acc_use_time1=lastnowstr;
	baseBean.acc_use_time2=nowstr;
	baseBean.user_name=username;
	baseBean.startnum=v_startnum;
	baseBean.pagenum=listpagenum;
	var baseBeanStr = JSON.encode(baseBean);
	$.getScript(rp+"/main/IndexPost.jsp?beanStr="+baseBeanStr+"&action=query",function(rs){});
}
// 删除详单
function delAccList(seq_id){
	var baseBean = {};
	baseBean.seq_id = seq_id;
	baseBean.user_name=username;
    var baseBeanStr = JSON.encode(baseBean);
	$.getScript(rp+"/main/IndexPost.jsp?beanStr="+baseBeanStr+"&action=del",function(rs){
	    // 查询全部详单
		queryAccList(1);
	});
}
//修改记录按钮事件
function listmodify(thisObj) {
	modaladdclear();
	$('#add_newbtn').html("修改");
	var $td = $(thisObj).parents('tr').children('td');
	$("#acc_select option[value='"+$td.eq(6).text()+"']").attr("selected", true);
	$("#accvalue").val(""+$td.eq(1).text()+"");
	$("#accdesc").val(""+$td.eq(2).text()+"");
	$("#acc_use_type_select option[value='"+$td.eq(7).text()+"']").attr("selected", true);
	$("#acc_my_card_select option[value='"+$td.eq(8).text()+"']").attr("selected", true);
	$("#acctime").val(""+$td.eq(5).text()+"");
	$("#accseqid").val(""+$td.eq(9).text()+"");
}
//删除记录按钮事件
function listdel(thisObj){
	var $td = $(thisObj).parents('tr').children('td');
	if(window.confirm('你确定要删除这条记录吗？')){
		delAccList($td.eq(9).text());
		return true;
	}else{
		return false;
	}
}

/***********界面新增*************/
// 分类-维表查询
function queryMyCard(startnum){
	var v_startnum = (startnum-1)*dimpagenum+1;
	var baseBean = {};
	baseBean.user_name=username;
	baseBean.startnum=v_startnum;
	baseBean.pagenum=dimpagenum;
    var baseBeanStr = JSON.encode(baseBean);
	$.getScript(rp+"/main/Dimquery.jsp?qryBean=mycard&beanStr="+baseBeanStr+"&action=query",function(rs){});
}
// 我的卡-维表查询
function queryUseType(startnum){
	var v_startnum = (startnum-1)*dimpagenum+1;
	var baseBean = {};
	baseBean.user_name=username;
	baseBean.startnum=v_startnum;
	baseBean.pagenum=dimpagenum;
    var baseBeanStr = JSON.encode(baseBean);
	$.getScript(rp+"/main/Dimquery.jsp?qryBean=usetype&beanStr="+baseBeanStr+"&action=query",function(rs){});
}
// 情况新增面板记录
function modaladdclear(){
	$("#accvalue").val("");
	$("#accdesc").val("");
	$("#acctime").val("");
}

/***********界面分类*************/
// 分类窗口记录清空
function modaltypeclear(){
	$('#modal_type_name').val("");
	$('#modal_type_desc').val("");
	$('#modal_type_seq').val("");
}
// 分类窗口记录修改
function modaltypemodify(thisObj){
	$('#modal_type_status').val("modify");
	$('#modal_type_btn1').html("保存");
	var $td = $(thisObj).parents('tr').children('td');
	$('#modal_type_name').val(""+$td.eq(0).text()+"");
	$('#modal_type_desc').val(""+$td.eq(1).text()+"");
	$('#modal_type_seq').val(""+$td.eq(2).text()+"");
	changemodaltype('modify');
}
// 分类窗口记录删除
function modaltypedel(thisObj){
	var $td = $(thisObj).parents('tr').children('td');
	if(window.confirm('你确定要删除这条记录吗？')){
		delmodaltypeact($td.eq(2).text());
		return true;
	}else{
		return false;
	}
}
// 删除分类
function delmodaltypeact(seq_id){
	var baseBean = {};
	baseBean.acc_sts = 0;
	baseBean.user_name=username;
	baseBean.acc_use_type = seq_id;
    var baseBeanStr = JSON.encode(baseBean);
	$.getScript(rp+"/main/Dimquery.jsp?qryBean=usetype&beanStr="+baseBeanStr+"&action=del",function(rs){
	    // 查询分类
		queryUseType(1);
	});
}
// 分类窗口元素状态改变
function changemodaltype(type){
	if(type=="add"||type=="modify"){
		var td = document.getElementById("modal_type_table");
		var pg = document.getElementById("div_modal_type_pagination");
		var query = document.getElementById("div_modal_type_query");
		//选中就添加覆盖层  
		var width = $(td).width();
		var height = $(td).height();
		height = height+$(pg).height()+$(query).height();
		var offset = $(td).offset();
		$("<div id=\"modal_type_table_div_cover\" style=\"width:" + width + "px;height:" + height 
		+ "px;left:" + 30 + "px;top:" + 15
		+ "px;opacity:0.5;position:absolute;z-index:9999;background-color:grey;\">&nbsp;</div>").appendTo(query);				
		$('#modal_type_name').attr("disabled",false);
		$('#modal_type_desc').attr("disabled",false);
	}else if(type=="init"){
		$('#modal_type_table_div_cover').remove();
		$('#modal_type_name').attr("disabled",true);
		$('#modal_type_desc').attr("disabled",true);
	}
}

/***********界面我的卡*************/
//我的卡窗口记录清空
function modalmycardclear(){
	$('#modal_mycard_name').val("");
	$('#modal_mycard_desc').val("");
	$('#modal_mycard_seq').val("");
}
// 我的卡窗口记录修改
function modalmycardmodify(thisObj){
	$('#modal_mycard_status').val("modify");
	$('#modal_mycard_btn1').html("保存");
	var $td = $(thisObj).parents('tr').children('td');
	$('#modal_mycard_name').val(""+$td.eq(0).text()+"");
	$('#modal_mycard_desc').val(""+$td.eq(1).text()+"");
	$('#modal_mycard_seq').val(""+$td.eq(2).text()+"");
	changemodalmycard('modify');
}		
// 我的卡窗口记录删除
function modalmycarddel(thisObj){
	var $td = $(thisObj).parents('tr').children('td');
	if(window.confirm('你确定要删除这条记录吗？')){
		delmodalmycardact($td.eq(2).text());
		return true;
	}else{
		return false;
	}
}
// 删除我的卡
function delmodalmycardact(seq_id){
	var baseBean = {};
	baseBean.acc_sts = 0;
	baseBean.user_name=username;
	baseBean.acc_my_card = seq_id;
    var baseBeanStr = JSON.encode(baseBean);
	$.getScript(rp+"/main/Dimquery.jsp?qryBean=mycard&beanStr="+baseBeanStr+"&action=del",function(rs){
	    // 查询我的卡
		queryMyCard(1);
	});
}
// 我的卡窗口元素状态改变
function changemodalmycard(type){
	if(type=="add"||type=="modify"){
		var td = document.getElementById("modal_mycard_table");
		var pg = document.getElementById("div_modal_mycard_pagination");
		var query = document.getElementById("div_modal_mycard_query");
		//选中就添加覆盖层  
		var width = $(td).width();
		var height = $(td).height();
		height = height+$(pg).height()+$(query).height();
		var offset = $(td).offset();
		$("<div id=\"modal_mycard_table_div_cover\" style=\"width:" + width + "px;height:" + height 
		+ "px;left:" + 30 + "px;top:" + 15
		+ "px;opacity:0.5;position:absolute;z-index:9999;background-color:grey;\">&nbsp;</div>").appendTo(query);				
		$('#modal_mycard_name').attr("disabled",false);
		$('#modal_mycard_desc').attr("disabled",false);
	}else if(type=="init"){
		$('#modal_mycard_table_div_cover').remove();
		$('#modal_mycard_name').attr("disabled",true);
		$('#modal_mycard_desc').attr("disabled",true);
	}
}

/***********进度条遮罩层*************/
// 显示或隐藏 flag:show,hide
function loadModal(flag){
	$("#loadingModal").modal(flag);
}