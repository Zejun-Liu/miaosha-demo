package com.jiuxian;

import com.jiuxian.base.util.IpUtil;
import org.junit.Test;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-25 11:53:00
 * Comment:
 */


public class IpUtilTest extends MiaoshaDemoV1ApplicationTests {

    @Test
    public void testS() {
        IpUtil.IpInfo ipInfo = IpUtil.getIpInfo("115.239.210.27");
        System.out.println(ipInfo);
    }
}
