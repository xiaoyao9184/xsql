package com.xy.xsql.tsql.model;

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

    default boolean hasChild(){
        return false;
    }

    default <T extends Block> List<T> toBlockList(){
        return null;
    }
}
