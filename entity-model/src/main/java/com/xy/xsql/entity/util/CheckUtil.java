package com.xy.xsql.entity.util;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Check
 * Created by xiaoyao9184 on 2016/1/15.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class CheckUtil {

    /**
     * Check Object NULL or Empty
     * @param obj Object
     * @return True/False
     */
    public static boolean isNull(Object obj){
        return  (obj == null);
    }

    /**
     * Check Object in Array with index is NULL or Empty
     * @param objArray Object Array
     * @param index Index
     * @return True/False
     */
    public static boolean isNull(Object[] objArray, int index) {
        return objArray == null ||
                (index + 1) > objArray.length ||
                CheckUtil.isNull(objArray[index]);
    }

    /**
     * Check String is NULL or Empty
     * @param str String
     * @return True/False
     */
    public static boolean isNullOrEmpty(String str){
        return str == null ||
                str.length() == 0;
    }

    /**
     * Check CharSequence is NULL or Empty
     * @param charSequence CharSequence
     * @return True/False
     */
    public static boolean isNullOrEmpty(CharSequence charSequence){
        return charSequence == null ||
                charSequence.length() == 0;
    }

    /**
     * Check Collection is NULL or Empty
     * @param collection Collection
     * @return True/False
     */
    public static boolean isNullOrEmpty(Collection collection){
        return collection == null ||
                collection.size() == 0;
    }

    /**
     * Check String Array is NULL or Empty
     * @param str String Array
     * @return True/False
     */
    public static boolean isNullOrEmpty(String[] str){
        return str == null ||
                str.length == 0;
    }

    /**
     * Check String is white space
     * @param str String
     * @return True/False
     */
    public static boolean isNullOrWhiteSpace(String str){
        return str == null ||
                Pattern.matches("^ *$", str);
    }

    /**
     * Check is start
     * @param obj Object
     * @return True/False
     */
    public static boolean isStart(Object obj){
        if(obj instanceof Boolean){
            return (Boolean)obj;
        }
        if(obj instanceof Number){
            Number i = (Number)obj;
            return i.intValue() == 0;
        }
        return false;
    }

    /**
     * Check all is start
     * @see #isStart(Object...)
     * @param objArray Object Array
     * @return True/False
     */
    public static boolean isStart(Object... objArray){
        for (Object obj: objArray) {
            if(isStart(obj)){
                return false;
            }
        }
        return true;
    }

}
