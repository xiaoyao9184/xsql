package com.xy.xsql.orm.data.sql.base;

import com.xy.xsql.orm.util.CheckUtil;

/**
 * 名称
 * 抽象元素
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class Name
        implements Cloneable{
    /**
     * 名称
     */
    private String realName;
    /**
     * 别名
     */
    private String otherName;


    public Name(String realName){
        this.realName = realName;
        this.otherName = null;
    }

    public Name(String realName, String otherName){
        this.realName = realName;
        this.otherName = otherName;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }


    /**
     * 获取 AS 语句
     * @return (table as t)
     */
    public String toAsSql(){
        if(!CheckUtil.isNullOrEmpty(otherName)){
            return realName + " AS " + otherName;
        }
        return realName;
    }

    /**
     * 获取选择语句
     * @return (table.*)
     */
    public String toPrefixSql(){
        if(!CheckUtil.isNullOrEmpty(otherName)){
            return otherName + ".";
        }
        return realName + ".";
    }

    /**
     * 是否使用别名
     * @return 是/否
     */
    public boolean isUseOtherName(){
        if(CheckUtil.isNullOrEmpty(this.otherName)){
            return false;
        }
        if(this.realName.equals(this.otherName)){
            return false;
        }
        return true;
    }

    /**
     * 克隆
     * @return Name
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Name clone() {
        return new Name(this.realName,this.otherName);
    }

}
