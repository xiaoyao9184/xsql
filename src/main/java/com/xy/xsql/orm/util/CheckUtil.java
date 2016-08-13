package com.xy.xsql.orm.util;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * 检验
 * Created by qyw on 2016/1/15.
 */
public class CheckUtil {

    /**
     * 检验是否是NULL
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj){
        return  (obj == null);
    }

    /**
     * 检验是否是NULL或空
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str){
        if(str == null ||
                str.length() == 0){
            return true;
        }
        return false;
    }

    /**
     * 检验是否是NULL或空
     * @param charSequence
     * @return
     */
    public static boolean isNullOrEmpty(CharSequence charSequence){
        if(charSequence == null ||
                charSequence.length() == 0){
            return true;
        }
        return false;
    }

    /**
     * 检验是否是NULL或空
     * @param collection
     * @return
     */
    public static boolean isNullOrEmpty(Collection collection){
        if(collection == null ||
                collection.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * 检验是否是NULL或空
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String[] str){
        if(str == null ||
                str.length == 0){
            return true;
        }
        return false;
    }

    /**
     * 检验是否是NULL或空格符
     * @param str
     * @return
     */
    public static boolean isNullOrWhiteSpace(String str){
        if(str == null ||
                Pattern.matches("^ *$",str)){
            return true;
        }
        return false;
    }

    /**
     * 验证是否是 Boolean:true 或 Integer:0
     * @param obj
     * @return
     */
    public static boolean isStart(Object obj){
        if(obj instanceof Boolean){
            Boolean bool = (Boolean)obj;
            return bool;
        }
        if(obj instanceof Number){
            Number i = (Number)obj;
            return i.intValue() == 0;
        }
        return false;
    }

    /**
     * 验证是否是 Boolean:true 或 Integer:0
     * @param objs
     * @return
     */
    public static boolean isStart(Object... objs){
        for (Object obj: objs) {
            if(isStart(obj)){
                return false;
            }
        }
        return true;
    }

}
