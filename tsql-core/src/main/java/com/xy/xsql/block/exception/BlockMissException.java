package com.xy.xsql.block.exception;

/**
 * Created by xiaoyao9184 on 2017/6/8.
 */
public class BlockMissException extends Exception {
    private Class<?> target;
    private String missPart;

    public BlockMissException(Class<?> target, String missPart){
        super(missPart);
        this.target = target;
        this.missPart = missPart;
    }
}
