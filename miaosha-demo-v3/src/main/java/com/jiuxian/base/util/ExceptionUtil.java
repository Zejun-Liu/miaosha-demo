package com.jiuxian.base.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 13:17:00
 * Comment:
 */


public class ExceptionUtil {

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
