package com.cqx.acc.service.client;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.cqx.acc.service.DealCountsDailyService;
import com.cqx.acc.service.bean.deal.AccCountsDailyBean;
import com.cqx.acc.service.bean.deal.DealCountsDailyRequestBean;
import com.cqx.acc.service.bean.deal.DealCountsDailyRequestObject;
import com.cqx.acc.service.bean.deal.DealCountsDailyResponseObject;
import com.cqx.acc.util.CommonUtils;
import com.cqx.acc.util.Constants;
import com.cqx.acc.util.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DealCountsDailyClient {

    private static Logger logger = LoggerFactory.getLogger(DealCountsDailyClient.class);
    private DealCountsDailyService dcds = (DealCountsDailyService) Constants.ctx.getBean("DealCountsDailyService");

    public void qryCountsDaily(DealCountsDailyRequestBean requestBean, HttpServletResponse response) {
        DealCountsDailyRequestObject request = new DealCountsDailyRequestObject();
        request.setBody(requestBean);
        // 自动设置报文头
        MessageHelper.autoSetRequestHeader(request, requestBean.getUser_name());
        DealCountsDailyResponseObject responseObj = dcds.qryCountsDaily(request);
        List<AccCountsDailyBean> resultList = (List<AccCountsDailyBean>) responseObj.getBody();
        //拼装Table
        StringBuffer sbTables = new StringBuffer("");
        sbTables.append("$('#query_table').html(\"");
        sbTables.append("<table class='table table-bordered table-hover table-condensed' id='query_table'>");
        sbTables.append("<thead>");
        sbTables.append("<tr>");
        sbTables.append("<th>类型</th>");
        sbTables.append("<th>值</th>");
        sbTables.append("<th>说明</th>");
        sbTables.append("<th>分类</th>");
        sbTables.append("<th>我的卡</th>");
        sbTables.append("<th>时间</th>");
        sbTables.append("<th>操作</th>");
        sbTables.append("</tr>");
        sbTables.append("</thead>");
        sbTables.append("<tbody>");
        if (resultList != null && resultList.size() > 0) {
            for (AccCountsDailyBean bean : resultList) {
                sbTables.append("<tr>");
                sbTables.append("<td>" + bean.getAcc_type_desc() + "</td>");//0
                sbTables.append("<td>" + bean.getAcc_value() + "</td>");//1
                sbTables.append("<td>" + CommonUtils.replace(bean.getAcc_desc()) + "</td>");//2
                sbTables.append("<td>" + bean.getAcc_use_name() + "</td>");//3
                sbTables.append("<td>" + bean.getAcc_card_name() + "</td>");//4
                sbTables.append("<td>" + bean.getAcc_use_time() + "</td>");//5
                sbTables.append("<td style='display:none'>" + bean.getAcc_type() + "</td>");//6
                sbTables.append("<td style='display:none'>" + bean.getAcc_use_type() + "</td>");//7
                sbTables.append("<td style='display:none'>" + bean.getAcc_card() + "</td>");//8
                sbTables.append("<td style='display:none'>" + bean.getSeq_id() + "</td>");//9
                sbTables.append("<td>");
                sbTables.append("<a onclick='listmodify(this);' href='#modal-container-add' role='button' class='btn btn-small btn-primary' data-toggle='modal'>编辑</a>");
                sbTables.append("&nbsp;<button onclick='listdel(this);' class='btn btn-small btn-primary' >删除</button>");
                sbTables.append("</td>");
                sbTables.append("</tr>");
            }
        }
        sbTables.append("</tbody>");
        sbTables.append("</table>");
        sbTables.append("\");");
        logger.debug("内容：{}", sbTables.toString());
        //分页
        StringBuffer sbPages = new StringBuffer();
        sbPages.append("plist.init(" + requestBean.getStartnum() + "," + requestBean.getPagenum()
                + "," + responseObj.getHeader().getTotalcount() + ",\"queryAccList\");");
        logger.debug("分页：{}", sbPages.toString());
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            // 内容
            out.print(sbTables.toString());
            // 分页
            out.print(sbPages.toString());
            // 取消遮罩层
            out.print("parent.loadModal(\"hide\");");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertCountsDaily(DealCountsDailyRequestBean requestBean, HttpServletResponse response) {
        DealCountsDailyRequestObject request = new DealCountsDailyRequestObject();
        request.setBody(requestBean);
        // 自动设置报文头
        MessageHelper.autoSetRequestHeader(request, requestBean.getUser_name());
        dcds.insertCountsDaily(request);
    }

    public void updateCountsDaily(DealCountsDailyRequestBean requestBean, HttpServletResponse response) {
        DealCountsDailyRequestObject request = new DealCountsDailyRequestObject();
        request.setBody(requestBean);
        // 自动设置报文头
        MessageHelper.autoSetRequestHeader(request, requestBean.getUser_name());
        dcds.updateCountsDaily(request);
    }

    public void delCountsDaily(DealCountsDailyRequestBean requestBean, HttpServletResponse response) {
        DealCountsDailyRequestObject request = new DealCountsDailyRequestObject();
        request.setBody(requestBean);
        // 自动设置报文头
        MessageHelper.autoSetRequestHeader(request, requestBean.getUser_name());
        dcds.delCountsDaily(request);
    }

    public void disableCountsDaily(DealCountsDailyRequestBean requestBean, HttpServletResponse response) {
        DealCountsDailyRequestObject request = new DealCountsDailyRequestObject();
        request.setBody(requestBean);
        // 自动设置报文头
        MessageHelper.autoSetRequestHeader(request, requestBean.getUser_name());
        dcds.disableCountsDaily(request);
    }
}
