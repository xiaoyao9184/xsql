package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.util.CheckUtil;

/**
 * 名称
 * 抽象元素
 * Created by xiaoyao9184 on 2016/1/16.
 */
public abstract class Name<THIS> extends Alias<THIS>
        implements Cloneable{
    /**
     * 名称
     */
    protected String name;


    public Name(String name){
        super(null);
        this.name = name;
    }

    public Name(String name, String aliasName){
        super(aliasName);
        this.name = name;
    }

    public Name(Name name){
        super(name.aliasName);
        this.name = name.name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public THIS withName(String name) {
        this.name = name;
        return (THIS)this;
    }



    /**
     * 获取 AS 语句
     * @return (table as t)
     */
    @Deprecated
    public String toAsSql(){
        if(!CheckUtil.isNullOrEmpty(aliasName)){
            return name + " AS " + aliasName;
        }
        return name;
    }

    /**
     * 获取选择语句
     * @return (table.*)
     */
    @Deprecated
    public String toPrefixSql(){
        if(!CheckUtil.isNullOrEmpty(aliasName)){
            return aliasName + ".";
        }
        return name + ".";
    }

    public abstract String getFullName();


    /**
     * 是否使用别名
     * @return 是/否
     */
    public boolean isUseOtherName(){
        if(CheckUtil.isNullOrEmpty(this.aliasName)){
            return false;
        }
        if(this.name.equals(this.aliasName)){
            return false;
        }
        return true;
    }
//
//    /**
//     * 克隆
//     * @return Name
//     */
//    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
//    @Override
//    public Name clone() {
//        return new Name(this.name,this.otherName);
//    }

}
