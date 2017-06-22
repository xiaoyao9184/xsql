package com.xy.xsql.block.core;

import com.xy.xsql.block.model.DataBlock;
import com.xy.xsql.tsql.model.Block;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public class DataBlockConverter<T> implements BlockConverter<T> {

    private DataBlock dataBlock;

    @Override
    public Block convert(T object){
        return dataBlock;
    }

    public DataBlockConverter<T> name(String name){
        dataBlock.setName(name);
        return this;
    }

    public DataBlockConverter<T> data(T data){
        dataBlock.setData(data);
        return this;
    }

    public DataBlockConverter<T> optional(){
        dataBlock.setOptional(true);
        return this;
    }





    public DataBlockConverter<T> optional(String name){
        dataBlock.setOptional(true);
        dataBlock.setName(name);
        return this;
    }

    public DataBlockConverter<T> must(String name){
        dataBlock.setOptional(false);
        dataBlock.setName(name);
        return this;
    }
}
