package com.xy.xsql.orm.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public class StringUtil {

    /**
     * Fill and join String with separator
     * @param fill Fill String
     * @param count Fill Count
     * @param separator Join separator String
     * @return String
     */
    public static String fillJoin(String fill, int count, String separator){
        String [] fills = new String[count];
        Arrays.fill(fills, fill);
        return StringUtils.join(fills,separator);
    }
}
