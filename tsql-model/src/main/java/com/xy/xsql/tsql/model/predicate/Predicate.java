package com.xy.xsql.tsql.model.predicate;


import com.xy.xsql.tsql.model.Block;

import java.util.List;

/**
 * Predicate
 * Created by xiaoyao9184 on 2017/3/12.
 */
public interface Predicate extends Block {

    /**
     * must override
     * @return
     */
    List<Block> toBlockList();
}
