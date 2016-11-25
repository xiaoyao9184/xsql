package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Alias<THIS> implements Element {

    protected String aliasName;

    public Alias(String aliasName){
        this.aliasName = aliasName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public THIS withAliasName(String aliasName) {
        this.aliasName = aliasName;
        return (THIS)this;
    }


    @Override
    public String toString(){
        return this.aliasName;
    }
}
