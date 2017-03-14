package com.xy.xsql.tsql.model.clause;


import com.xy.xsql.tsql.model.Block;

import java.util.List;

/**
 * Clause
 * Created by xiaoyao9184 on 2017/3/12.
 */
public interface Clause extends Block {

    /**
     * must override
     * @return
     */
    List<Block> toBlockList();
}
