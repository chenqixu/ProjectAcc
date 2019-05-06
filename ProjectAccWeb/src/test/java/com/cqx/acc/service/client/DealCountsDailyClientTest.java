package com.cqx.acc.service.client;

import com.cqx.acc.service.bean.deal.DealCountsDailyRequestBean;
import com.cqx.acc.util.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DealCountsDailyClientTest {

    private DealCountsDailyRequestBean dcdrbean = new DealCountsDailyRequestBean();
    private DealCountsDailyClient client = new DealCountsDailyClient();

    @Before
    public void setUp() throws Exception {
        // 初始化日志和服务
        Constants.init();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void qryCountsDaily() {
        dcdrbean.setAcc_use_time1("2019-04-03");
        dcdrbean.setAcc_use_time2("2019-04-04");
        dcdrbean.setAcc_card("28");
        dcdrbean.setStartnum("1");
        dcdrbean.setPagenum("15");
        client.qryCountsDaily(dcdrbean, null);
    }

    @Test
    public void insertCountsDaily() {
    }

    @Test
    public void updateCountsDaily() {
    }

    @Test
    public void delCountsDaily() {
    }

    @Test
    public void disableCountsDaily() {
    }
}