package com.xy.xsql.core.selecter;

/**
 * Created by xiaoyao9184 on 2017/10/23.
 */
public interface LeftRightSelector<LEFT,RIGHT> {

    LEFT left();

    RIGHT right();

}
