package com.xy.xsql.core.holder;

/**
 * ParentHolder
 * Created by xiaoyao9184 on 2016/12/4.
 */
public interface ParentHolder<This,Parent> {

    This in(Parent done);

    Parent out();
}
