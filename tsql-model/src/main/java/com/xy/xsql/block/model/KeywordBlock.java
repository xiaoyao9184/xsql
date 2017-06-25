package com.xy.xsql.block.model;

/**
 * Created by xiaoyao9184 on 2017/6/6.
 */
public class KeywordBlock {

    private String keyword;

    public KeywordBlock(String keyword){
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return this.keyword;
    }

}
