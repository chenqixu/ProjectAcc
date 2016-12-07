package com.cqx.acc.service.client;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.cqx.acc.service.JsonService;
import com.cqx.acc.service.bean.AccTable;
import com.cqx.acc.service.bean.dim.Acc_my_card;
import com.cqx.acc.service.bean.dim.Acc_use_type;
import com.cqx.acc.service.bean.json.JsonRequestObject;
import com.cqx.acc.service.bean.json.JsonResponseObject;
import com.cqx.acc.util.CommonUtils;
import com.cqx.acc.util.Constants;
import com.cqx.acc.util.MessageHelper;

public class DimClient {
	@SuppressWarnings("unchecked")
	public void qryMyCard(Acc_my_card requestBean, HttpServletResponse response){
		List<Acc_my_card> resultList = new ArrayList<Acc_my_card>();
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_my_card");
		at.setTablecolum("acc_my_card,acc_card_name,acc_card_desc");
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		JsonResponseObject responseJS = service.qry(requestJson);
		List<String> list = (List<String>)responseJS.getBody();
		if(list!=null){
			Iterator<String> it = list.iterator();
			while(it.hasNext()){
				resultList.add(new Acc_my_card().jsonToBean(it.next()));
			}
			//拼装Table
			StringBuffer sbTables = new StringBuffer("");
			sbTables.append("$('#modal_mycard_table').html(\"");
			sbTables.append("<table class='table' id='modal_mycard_table'>");
			sbTables.append("<thead>");
			sbTables.append("<tr>");
			sbTables.append("<th>名称</th>");
			sbTables.append("<th>说明</th>");
			sbTables.append("<th>操作</th>");
			sbTables.append("</tr>");
			sbTables.append("</thead>");
			sbTables.append("<tbody>");
			if(resultList.size()>0){
				for(Acc_my_card bean : resultList){
					sbTables.append("<tr>");
					sbTables.append("<td>"+bean.getAcc_card_name()+"</td>");
					sbTables.append("<td>"+bean.getAcc_card_desc()+"</td>");
					sbTables.append("<td style='display:none'>"+bean.getAcc_my_card()+"</td>");
					sbTables.append("<td><button class='btn btn-small btn-primary' onclick='modalmycardmodify(this);'>编辑</button>");
					sbTables.append("&nbsp;<button class='btn btn-small btn-primary' onclick='modalmycarddel(this);'>删除</button></td>");
					sbTables.append("</tr>");	
				}
			}
			sbTables.append("</tbody>");
			sbTables.append("</table>");
			sbTables.append("\");");
			//分页
			StringBuffer sbPages = new StringBuffer(); 
			sbPages.append("pmycardlist.init("+requestBean.getStartnum()+","+requestBean.getPagenum()
					+","+responseJS.getHeader().getTotalcount()+",\"queryMyCard\");");
			//Select，下拉框必须全部查询
			List<Acc_my_card> resultSelectList = new ArrayList<Acc_my_card>();
			requestBean.setStartnum("1");
			requestBean.setPagenum("1000");
			at.setRequestObjbean(requestBean);
			requestJson.setBody(JSONObject.fromObject(at).toString());
			// 自动设置报文头
			MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
			JsonResponseObject responseSelectJS = service.qry(requestJson);
			List<String> selectlist = (List<String>)responseSelectJS.getBody();
			if(selectlist!=null){
				Iterator<String> selectit = selectlist.iterator();
				while(selectit.hasNext()){
					resultSelectList.add(new Acc_my_card().jsonToBean(selectit.next()));
				}
			}			
			//拼装select
			StringBuffer sbSelects = new StringBuffer("");
			sbSelects.append("$('#acc_my_card_select').html(\"");
			sbSelects.append("<select id='acc_my_card_select' class='form-control'>");
			if(resultSelectList.size()>0){
				for(Acc_my_card mycard : resultSelectList){
					sbSelects.append("<option selected value='"+mycard.getAcc_my_card()+"'>"+mycard.getAcc_card_name()+"</option>");
				}
			}
			sbSelects.append("</select>");
			sbSelects.append("\");");
			try{
				response.setContentType("text/html");
				response.setCharacterEncoding("GBK");
				PrintWriter out = null;
				out = response.getWriter();
				out.print(sbTables.toString());
				out.print(sbSelects.toString());
				out.print(sbPages.toString());
				out.flush();
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
//		return sbTables.toString();
//		JSONArray resultlistjsonstr = JSONArray.fromObject(resultList);
//		return resultlistjsonstr.toString();
	}

	@SuppressWarnings("unchecked")
	public void qryUsetype(Acc_use_type requestBean, HttpServletResponse response){
		List<Acc_use_type> resultList = new ArrayList<Acc_use_type>();
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_use_type");
		at.setTablecolum("acc_use_type,acc_use_name,acc_use_desc");
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		JsonResponseObject responseJson = service.qry(requestJson);
		List<String> list = (List<String>)responseJson.getBody();
		if(list!=null){
			Iterator<String> it = list.iterator();
			while(it.hasNext()){
				resultList.add(new Acc_use_type().jsonToBean(it.next()));
			}
			//拼装Table
			StringBuffer sbTables = new StringBuffer("");
			sbTables.append("$('#modal_type_table').html(\"");
			sbTables.append("<table class='table' id='modal_type_table'>");
			sbTables.append("<thead>");
			sbTables.append("<tr>");
			sbTables.append("<th>名称</th>");
			sbTables.append("<th>说明</th>");
			sbTables.append("<th>操作</th>");
			sbTables.append("</tr>");
			sbTables.append("</thead>");
			sbTables.append("<tbody>");
			if(resultList.size()>0){
				for(Acc_use_type bean : resultList){
					sbTables.append("<tr>");
					sbTables.append("<td>"+bean.getAcc_use_name()+"</td>");
					sbTables.append("<td>"+bean.getAcc_use_desc()+"</td>");
					sbTables.append("<td style='display:none'>"+bean.getAcc_use_type()+"</td>");
					sbTables.append("<td><button class='btn btn-small btn-primary' onclick='modaltypemodify(this);'>编辑</button>");
					sbTables.append("&nbsp;<button class='btn btn-small btn-primary' onclick='modaltypedel(this);'>删除</button></td>");
					sbTables.append("</tr>");	
				}
			}
			sbTables.append("</tbody>");
			sbTables.append("</table>");
			sbTables.append("\");");
			//分页
			StringBuffer sbPages = new StringBuffer(); 
			sbPages.append("ptypelist.init("+requestBean.getStartnum()+","+requestBean.getPagenum()
					+","+responseJson.getHeader().getTotalcount()+",\"queryUseType\");");
			//Select，下拉框必须全部查询
			List<Acc_use_type> resultSelectList = new ArrayList<Acc_use_type>();
			requestBean.setStartnum("1");
			requestBean.setPagenum("1000");
			at.setRequestObjbean(requestBean);
			requestJson.setBody(JSONObject.fromObject(at).toString());
			// 自动设置报文头
			MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
			JsonResponseObject responseSelectJson = service.qry(requestJson);
			List<String> selectlist = (List<String>)responseSelectJson.getBody();
			if(selectlist!=null){
				Iterator<String> selectit = selectlist.iterator();
				while(selectit.hasNext()){
					resultSelectList.add(new Acc_use_type().jsonToBean(selectit.next()));
				}
			}			
			//拼装select
			StringBuffer sbSelects = new StringBuffer("");
			sbSelects.append("$('#acc_use_type_select').html(\"");
			sbSelects.append("<select id='acc_use_type_select' class='form-control'>");
			if(resultList.size()>0){
				for(Acc_use_type bean : resultSelectList){
					sbSelects.append("<option selected value='"+bean.getAcc_use_type()+"'>");
					sbSelects.append(bean.getAcc_use_name()+"</option>");
				}
			}
			sbSelects.append("</select>");
			sbSelects.append("\");");
			try{
				response.setContentType("text/html");
				response.setCharacterEncoding("GBK");
				PrintWriter out = null;
				out = response.getWriter();
				out.print(sbTables.toString());
				out.print(sbSelects.toString());
				out.print(sbPages.toString());
				out.flush();
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
//		return sbTables.toString();
//		JSONArray resultlistjsonstr = JSONArray.fromObject(resultList);
//		return resultlistjsonstr.toString();
	}
	
	public void insertMyCard(Acc_my_card requestBean, HttpServletResponse response){
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_my_card");
//		at.setTablecolum("(acc_my_card,acc_card_name,acc_card_desc,user_name,acc_sts) values(NEXTVAL('MyCardSeq'),?,?,?,?)");
		at.setTablecolum("(acc_my_card,acc_card_name,acc_card_desc,user_name,acc_sts) select max(acc_my_card)+1,?,?,?,? from acc_my_card");
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		service.insert(requestJson);
	}
	
	public void updateMyCard(Acc_my_card requestBean, HttpServletResponse response){
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_my_card");
		StringBuffer sb = new StringBuffer();
		if(CommonUtils.IsNotEmpty(requestBean.getAcc_card_desc())){
			sb.append(" acc_card_desc = ?,");
		}
		if(CommonUtils.IsNotEmpty(requestBean.getAcc_card_name())){
			sb.append(" acc_card_name = ?,");
		}
		if(sb!=null && sb.length()>0){
			// 删掉最后一个逗号
			sb.deleteCharAt(sb.length()-1);
		}
		if(CommonUtils.IsNotEmpty(requestBean.getAcc_my_card())){
			sb.append(" where acc_my_card = ? ");
		}		
		at.setTablecolum(sb.toString());
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		service.update(requestJson);
	}
	
	public void disableMyCard(Acc_my_card requestBean, HttpServletResponse response){
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_my_card");
		at.setTablecolum(" acc_sts = ? where acc_my_card = ? ");
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		service.update(requestJson);
	}
	
	public void insertUsetype(Acc_use_type requestBean, HttpServletResponse response){
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_use_type");
//		at.setTablecolum("(acc_use_type,acc_use_name,acc_use_desc,user_name,acc_sts) values(NEXTVAL('UseTypeSeq'),?,?,?,?)");
		at.setTablecolum("(acc_use_type,acc_use_name,acc_use_desc,user_name,acc_sts) select max(acc_use_type)+1,?,?,?,? from acc_use_type");
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		service.insert(requestJson);
	}
	
	public void updateUsetype(Acc_use_type requestBean, HttpServletResponse response){
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_use_type");
		StringBuffer sb = new StringBuffer();
		if(CommonUtils.IsNotEmpty(requestBean.getAcc_use_desc())){
			sb.append(" acc_use_desc = ?,");
		}
		if(CommonUtils.IsNotEmpty(requestBean.getAcc_use_name())){
			sb.append(" acc_use_name = ?,");
		}
		if(sb!=null && sb.length()>0){
			// 删掉最后一个逗号
			sb.deleteCharAt(sb.length()-1);
		}
		if(CommonUtils.IsNotEmpty(requestBean.getAcc_use_type())){
			sb.append(" where acc_use_type = ? ");
		}		
		at.setTablecolum(sb.toString());
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		service.update(requestJson);
	}
	
	public void disableUsetype(Acc_use_type requestBean, HttpServletResponse response){
		JsonService service = (JsonService)Constants.ctx.getBean("JsonService");
		JsonRequestObject requestJson = new JsonRequestObject();
		AccTable at = new AccTable();
		at.setPackagestr("com.cqx.acc.service.bean.dim");
		at.setTablename("acc_use_type");
		at.setTablecolum(" acc_sts = ? where acc_use_type = ? ");
		at.setRequestObjbean(requestBean);
		requestJson.setBody(JSONObject.fromObject(at).toString());
		// 自动设置报文头
		MessageHelper.autoSetRequestHeader(requestJson, requestBean.getUser_name());
		service.update(requestJson);
	}
}
