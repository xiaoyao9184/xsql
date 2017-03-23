package com.xy.xsql.entity.model.template;

/**
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class AliasName<THIS> {
    protected String name;
    protected String aliasName;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAliasName() {
        return aliasName;
    }
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    /**
     * 设置 名称
     * @param name 名称
     * @return THIS
     */
    public THIS withName(String name) {
        this.name = name;
        return (THIS)this;
    }
    /**
     * 设置 别名
     * @param aliasName 别名
     * @return THIS
     */
    public THIS withAliasName(String aliasName) {
        this.aliasName = aliasName;
        return (THIS)this;
    }
}
