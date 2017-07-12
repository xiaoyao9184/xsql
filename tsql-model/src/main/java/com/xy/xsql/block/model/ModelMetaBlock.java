package com.xy.xsql.block.model;

/**
 * Created by xiaoyao9184 on 2017/7/10.
 */
public class ModelMetaBlock implements Block {

    private BlockMeta meta;
    private Object model;

    public BlockMeta getMeta() {
        return meta;
    }

    public void setMeta(BlockMeta meta) {
        this.meta = meta;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

}
