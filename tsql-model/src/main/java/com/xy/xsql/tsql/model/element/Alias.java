package com.xy.xsql.tsql.model.element;

import com.xy.xsql.tsql.model.Block;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Alias<THIS> implements Block {

    protected String aliasName;

    public Alias() {
    }

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
