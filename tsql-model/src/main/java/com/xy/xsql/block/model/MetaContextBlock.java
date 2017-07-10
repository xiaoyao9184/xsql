package com.xy.xsql.block.model;

/**
 * Created by xiaoyao9184 on 2017/7/10.
 */
public class MetaContextBlock implements Block {

    private BlockMeta meta;
    private Object context;

    public BlockMeta getMeta() {
        return meta;
    }

    public void setMeta(BlockMeta meta) {
        this.meta = meta;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

}
