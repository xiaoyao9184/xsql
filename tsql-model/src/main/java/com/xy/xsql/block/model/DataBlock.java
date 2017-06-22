package com.xy.xsql.block.model;

import com.xy.xsql.tsql.model.Block;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public class DataBlock implements Block {

    private boolean optional;
    private Object data;
    private String name;

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO
    @Override
    public boolean hasChild() {
        return false;
    }

    @Override
    public List<Block> toBlockList() {
        return null;
    }
}
