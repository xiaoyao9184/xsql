package com.xy.xsql.orm.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Join if not NULL or Empty
     * @param separator Separator
     * @param strings String Array
     * @return String
     */
    public static String join(String separator,String... strings){
        List<String> stringList = new ArrayList<>();
        for (String str: strings) {
            if(!CheckUtil.isNullOrEmpty(str)){
                stringList.add(str);
            }
        }
        return StringUtils.join(stringList,separator);
    }
}
