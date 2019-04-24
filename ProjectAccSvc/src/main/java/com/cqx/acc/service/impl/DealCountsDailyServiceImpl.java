package com.cqx.acc.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cqx.acc.service.DealCountsDailyService;
import com.cqx.acc.service.bean.AccBooleanResponseObject;
import com.cqx.acc.service.bean.deal.AccCountsDailyBean;
import com.cqx.acc.service.bean.deal.AccSumCountsDailyBean;
import com.cqx.acc.service.bean.deal.DealCountsDailyRequestBean;
import com.cqx.acc.service.bean.deal.DealCountsDailyRequestObject;
import com.cqx.acc.service.bean.deal.DealCountsDailyResponseObject;
import com.cqx.acc.util.AccResultSet;
import com.cqx.acc.util.AccStringUtils;
import com.cqx.acc.util.CommonUtils;
import com.cqx.acc.util.Constants;
import com.cqx.acc.util.DataSourceUtils;
import com.cqx.acc.util.MessageHelper;

public class DealCountsDailyServiceImpl implements DealCountsDailyService {

	@Override
	public DealCountsDailyResponseObject qryCountsDaily(
			DealCountsDailyRequestObject requestObj) {
		DealCountsDailyRequestBean requestbean = (DealCountsDailyRequestBean)requestObj.getBody();
		DealCountsDailyResponseObject responseObj = new DealCountsDailyResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select seq_id,acc_time,acc_type,acc_value,t1.acc_sts,acc_desc,"+
				"t1.acc_use_type,acc_card,acc_use_time,t1.user_name ");
		sql.append(",t2.acc_card_name,t3.acc_use_name ");
		sql.append(",case when acc_type=0 then '平衡' when acc_type=1 then '收入' when acc_type=2 then '支出' when acc_type=3 then '透支' end as acc_type_desc ");
		sql.append(" from acc_counts_daily t1 ");
		sql.append(" left join acc_my_card t2 on t1.acc_card=t2.acc_my_card and t2.acc_sts=1 and t2.user_name=t1.user_name ");
		sql.append(" left join acc_use_type t3 on t1.acc_use_type=t3.acc_use_type and t3.acc_sts=1 and t3.user_name=t1.user_name ");
		sql.append(" where t1.acc_sts=1 ");
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_time1())){
			sql.append(" and t1.acc_use_time >= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_time2())){
			sql.append(" and t1.acc_use_time <= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_type())){
			sql.append(" and t1.acc_type = ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_value1())){
			sql.append(" and t1.acc_value >= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_value2())){
			sql.append(" and t1.acc_value <= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_type())){
			sql.append(" and t1.acc_use_type = ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_card())){
			sql.append(" and t1.acc_card = ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getUser_name())){
			sql.append(" and t1.user_name = ? ");
		}
		sql.append(" order by acc_use_time desc,seq_id desc ");
		if(!StringUtils.isEmpty(requestbean.getStartnum())){
			sql.append(" limit ")
				.append(Integer.parseInt(requestbean.getStartnum())-1)
				.append(" , ")
				.append(requestbean.getPagenum());
		}
		CommonUtils.info("[sql]"+sql.toString());
		AccResultSet ars = DataSourceUtils.query(sql.toString(), requestbean, AccCountsDailyBean.class);
		List<Object> result_list = ars.getResult();
		responseObj.getHeader().setTotalcount(ars.getTotalcount());
		responseObj.setBody(result_list);
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

	@Override
	public AccBooleanResponseObject insertCountsDaily(
			DealCountsDailyRequestObject requestObj) {
		DealCountsDailyRequestBean requestbean = (DealCountsDailyRequestBean)requestObj.getBody();
		AccBooleanResponseObject responseObj = new AccBooleanResponseObject();
		try{
			// 判断是不是UTF-8编码
			if (requestbean.getAcc_desc().equals(
					new String(requestbean.getAcc_desc().getBytes(Constants.DECODE), Constants.DECODE))) {
				// 转码，app会乱码必须转
				requestbean.setAcc_desc(CommonUtils.decode(requestbean.getAcc_desc()));
			}
		}catch(Exception e){
			CommonUtils.error(e.getMessage());
		}
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("insert into acc_counts_daily(seq_id,acc_time,acc_type,acc_value,acc_sts,acc_desc,"+
				"acc_use_type,acc_card,acc_use_time,user_name) "+
//				" values(NEXTVAL('CountsDailySeq'),now(),?,?,?,?,?,?,?,?) ");
				" select max(seq_id)+1,now(),?,?,?,?,?,?,?,? from acc_counts_daily");
		CommonUtils.info("[sql]"+sql.toString());
		int result_code = DataSourceUtils.update(sql.toString(), requestbean);
		responseObj.setBody(String.valueOf(result_code));
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

	@Override
	public AccBooleanResponseObject updateCountsDaily(
			DealCountsDailyRequestObject requestObj) {
		DealCountsDailyRequestBean requestbean = (DealCountsDailyRequestBean)requestObj.getBody();
		AccBooleanResponseObject responseObj = new AccBooleanResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("update acc_counts_daily set acc_time = now(),");
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_time1())){
			sql.append(" acc_use_time = ?,");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_type())){
			sql.append(" acc_type = ?,");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_value1())){
			sql.append(" acc_value = ?,");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_type())){
			sql.append(" acc_use_type = ?,");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_card())){
			sql.append(" acc_card = ?, ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_desc())){
			sql.append(" acc_desc = ?,");
		}
		// 删掉最后一个逗号
		sql.deleteCharAt(sql.length()-1);
		sql.append(" where seq_id = ? ");
		CommonUtils.info("[sql]"+sql.toString());
		int result_code = DataSourceUtils.update(sql.toString(), requestbean);
		responseObj.setBody(String.valueOf(result_code));
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

	@Override
	public AccBooleanResponseObject delCountsDaily(
			DealCountsDailyRequestObject requestObj) {
		DealCountsDailyRequestBean requestbean = (DealCountsDailyRequestBean)requestObj.getBody();
		AccBooleanResponseObject responseObj = new AccBooleanResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("delete from acc_counts_daily where seq_id = ? ");
		CommonUtils.info("[sql]"+sql.toString());
		int result_code = DataSourceUtils.update(sql.toString(), requestbean);
		responseObj.setBody(String.valueOf(result_code));
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

	@Override
	public AccBooleanResponseObject disableCountsDaily(
			DealCountsDailyRequestObject requestObj) {
		DealCountsDailyRequestBean requestbean = (DealCountsDailyRequestBean)requestObj.getBody();
		AccBooleanResponseObject responseObj = new AccBooleanResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("update acc_counts_daily set acc_sts=0,acc_time=now() where seq_id = ? ");
		CommonUtils.info("[sql]"+sql.toString());
		int result_code = DataSourceUtils.update(sql.toString(), requestbean);
		responseObj.setBody(String.valueOf(result_code));
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}

	@Override
	public AccBooleanResponseObject sumCountsDaily(
			DealCountsDailyRequestObject requestObj) {
		DealCountsDailyRequestBean requestbean = (DealCountsDailyRequestBean)requestObj.getBody();
		AccBooleanResponseObject responseObj = new AccBooleanResponseObject();
		// 自动设置报文头
		MessageHelper.autoSetRespHeader(responseObj);
		// 鉴权
		if(!MessageHelper.authority(requestObj.getHeader())){
			// 自动设置鉴权失败返回结果
			MessageHelper.autoSetAuthResult(responseObj);
			return responseObj;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(t1.acc_value) as acc_value from acc_counts_daily t1 ");
		sql.append(" where t1.acc_sts=1 ");
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_time1())){
			sql.append(" and t1.acc_use_time >= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_time2())){
			sql.append(" and t1.acc_use_time <= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_type())){
			sql.append(" and t1.acc_type = ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_value1())){
			sql.append(" and t1.acc_value >= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_value2())){
			sql.append(" and t1.acc_value <= ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_use_type())){
			sql.append(" and t1.acc_use_type = ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getAcc_card())){
			sql.append(" and t1.acc_card = ? ");
		}
		if(AccStringUtils.IsNotEmpty(requestbean.getUser_name())){
			sql.append(" and t1.user_name = ? ");
		}
		CommonUtils.info("[sql]"+sql.toString());
		AccResultSet ars = DataSourceUtils.query(sql.toString(), requestbean, AccSumCountsDailyBean.class);
		List<Object> result_list = ars.getResult();
		String result_value = "0";
		if(result_list!=null && result_list.size()==1){
			result_value = ((AccSumCountsDailyBean)result_list.get(0)).getAcc_value();
		}
		responseObj.setBody(result_value);
		MessageHelper.autoSetDealTime(responseObj);
		return responseObj;
	}
}
