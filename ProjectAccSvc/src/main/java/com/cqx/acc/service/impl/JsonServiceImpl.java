package com.cqx.acc.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.cqx.acc.service.JsonService;
import com.cqx.acc.service.bean.AccBooleanResponseObject;
import com.cqx.acc.service.bean.AccTable;
import com.cqx.acc.service.bean.intf.AccAllBean;
import com.cqx.acc.service.bean.json.JsonRequestObject;
import com.cqx.acc.service.bean.json.JsonResponseObject;
import com.cqx.acc.util.AccResultSet;
import com.cqx.acc.util.CommonUtils;
import com.cqx.acc.util.DataSourceUtils;
import com.cqx.acc.util.MessageHelper;

public class JsonServiceImpl implements JsonService {

	@Override
	public JsonResponseObject qry(JsonRequestObject requestObj) {
		AccTable at = new AccTable().jsonToBean((String)requestObj.getBody());
		JsonResponseObject responseObj = new JsonResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		String packagestr = at.getPackagestr();
		String tablename = at.getTablename();
		String tablecolum = at.getTablecolum();
		AccAllBean requestObjbean = null;
		try {
			requestObjbean = (AccAllBean)JSONObject.toBean(JSONObject.fromObject(at.getRequestObjbean()), Class.forName(packagestr+"."+CommonUtils.captureName(tablename)));
		} catch (ClassNotFoundException e1) {
			CommonUtils.error(e1.getMessage());
		}		
		StringBuffer sql = new StringBuffer();
		sql.append("select ")
			.append(tablecolum)
			.append(" from ")
			.append(tablename)
			.append(" where acc_sts=1 and user_name=? ");
		if(!StringUtils.isEmpty(requestObjbean.getStartnum())){
			sql.append(" limit ")
				.append(Integer.parseInt(requestObjbean.getStartnum())-1)
				.append(" , ")
				.append(requestObjbean.getPagenum());
		}
		CommonUtils.info("[sql]"+sql.toString());
		List<String> result_list = new ArrayList<String>();
		List<Object> query_result_list = null;
		AccResultSet ars = null;
		try {
			ars = DataSourceUtils.query(sql.toString(),
					JSONObject.toBean(JSONObject.fromObject(requestObjbean), Class.forName(packagestr+"."+CommonUtils.captureName(tablename))),
					Class.forName(packagestr+"."+CommonUtils.captureName(tablename)));
			query_result_list = ars.getResult();
			for(Object obj : query_result_list){
				result_list.add(JSONObject.fromObject(obj).toString());
			}
		} catch (ClassNotFoundException e) {
			CommonUtils.error(e.getMessage());
		}
		responseObj.getHeader().setTotalcount(ars.getTotalcount());
		responseObj.setBody(result_list);
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

	@Override
	public AccBooleanResponseObject insert(JsonRequestObject requestObj) {
		AccTable at = new AccTable().jsonToBean((String)requestObj.getBody());
		AccBooleanResponseObject responseObj = new AccBooleanResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		String packagestr = at.getPackagestr();
		String tablename = at.getTablename();
		String tablecolum = at.getTablecolum();
		Object requestObjbean = at.getRequestObjbean();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ")
			.append(tablename)
			.append(tablecolum);
		CommonUtils.info("[sql]"+sql.toString());
		int result_code = 0;
		try{
			result_code = DataSourceUtils.update(sql.toString(), 
				JSONObject.toBean(JSONObject.fromObject(requestObjbean), Class.forName(packagestr+"."+CommonUtils.captureName(tablename))));
		}catch(Exception e){
			CommonUtils.error(e.getMessage());
		}
		responseObj.setBody(String.valueOf(result_code));
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

	@Override
	public AccBooleanResponseObject update(JsonRequestObject requestObj) {
		AccTable at = new AccTable().jsonToBean((String)requestObj.getBody());
		AccBooleanResponseObject responseObj = new AccBooleanResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		String packagestr = at.getPackagestr();
		String tablename = at.getTablename();
		String tablecolum = at.getTablecolum();
		Object requestObjbean = at.getRequestObjbean();
		StringBuffer sql = new StringBuffer();
		sql.append("update ")
			.append(tablename)
			.append(" set ")
			.append(tablecolum);
		CommonUtils.info("[sql]"+sql.toString());		
		int result_code = 0;
		try{
			result_code = DataSourceUtils.update(sql.toString(), 
				JSONObject.toBean(JSONObject.fromObject(requestObjbean), Class.forName(packagestr+"."+CommonUtils.captureName(tablename))));
		}catch(Exception e){
			CommonUtils.error(e.getMessage());
		}
		responseObj.setBody(String.valueOf(result_code));
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

}
