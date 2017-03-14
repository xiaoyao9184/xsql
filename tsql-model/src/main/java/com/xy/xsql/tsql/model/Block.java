package com.xy.xsql.orm.data.tsql;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public interface Block {
    /**
     * to string
     * @return String
     */
    String toString();

    default List<Block> toBlockList(){
        return null;
    }
}
