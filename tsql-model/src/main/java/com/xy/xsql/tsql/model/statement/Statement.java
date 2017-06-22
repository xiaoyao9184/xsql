package com.xy.xsql.tsql.model.statement;


import com.xy.xsql.tsql.model.Block;

import java.util.List;

/**
 * Statement
 * Created by xiaoyao9184 on 2017/3/12.
 */
public interface Statement extends Block {

    @Override
    default boolean hasChild() {
        return true;
    }

    /**
     * must override
     * @return
     */
    List<Block> toBlockList();
}
