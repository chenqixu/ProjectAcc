package com.cqx.acc.util;

import org.junit.Test;

public class KEYUtilsTest {

    @Test
    public void getKEYByNameAndId() {
        String name = "cqx";
        String id = "61832587718812523095";
        String sed = "20240713164621";
        String result = KEYUtils.stringToMD5(sed.substring(0, sed.length() - 1) + name + id);
        System.out.println(result);
    }
}
