package com.jiuxian.base.util;

import com.jiuxian.base.util.ExceptionUtil;
import com.jiuxian.base.util.JSONUtil;
import com.jiuxian.base.web.RestResponse;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 13:13:00
 * Comment:
 */

@Slf4j
public class ActionUtil {

    private static void setHeader(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
    }

    public static void handleResponse(HttpServletResponse response, RestResponse restResponse) {
        setHeader(response);
        try {
            PrintWriter out = response.getWriter();
            out.write(JSONUtil.toJSON(restResponse));
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }


}
