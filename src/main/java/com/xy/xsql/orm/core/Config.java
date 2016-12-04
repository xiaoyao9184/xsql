package com.xy.xsql.orm.core;

/**
 * Config
 * Created by xiaoyao9184 on 2016/12/4.
 */
public interface Config<This,Done> {

    This start(Done done);

    Done done();
}
