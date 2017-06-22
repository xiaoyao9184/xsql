package com.xy.xsql.block.model;

import com.xy.xsql.tsql.model.Block;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/6.
 */
public class KeywordBlock implements Block {

    private String keyword;

    public KeywordBlock(String keyword){
        this.keyword = keyword;
    }

    @Override
    public boolean hasChild() {
        return false;
    }

    @Override
    public List<Block> toBlockList() {
        return null;
    }

    @Override
    public String toString() {
        return this.keyword;
    }
}
