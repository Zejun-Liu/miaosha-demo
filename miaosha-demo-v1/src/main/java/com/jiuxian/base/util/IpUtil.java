package com.jiuxian.base.util;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.jiuxian.base.web.WebContextHolder;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * @author liuzejun
 */
public class IpUtil {

    private final static RestTemplate restTemplate = WebContextHolder.getApplicationContext().getBean(RestTemplate.class);

    /**
     * 获取登录用户的IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 通过IP获取地址(需要联网，调用淘宝的IP库)
     * @param ip
     * @return
     */
    public static IpInfo getIpInfo(String ip) {
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip={?}";
        return restTemplate.getForObject(url, IpInfo.class, ip);
    }

    public static void main(String[] args) {
        IpInfo ipInfo = getIpInfo("115.239.210.27");
        System.out.println(ipInfo);
    }

    @Data
    public static class IpInfo {
        private Integer code;
        private IpData data;
    }

    @Data
    public static class IpData {
        private String ip;

        private String country;

        private String area;

        private String region;

        private String city;

        private String county;

        private String isp;

        @JsonAlias("country_id")
        private String countryId;

        @JsonAlias("area_id")
        private String areaId;

        @JsonAlias("region_id")
        private String regionId;

        @JsonAlias("city_id")
        private String cityId;

        @JsonAlias("county_id")
        private String countyId;

        @JsonAlias("isp_id")
        private String ispId;
    }
}