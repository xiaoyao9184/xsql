package com.xy.xsql.orm.build;

/**
 * Config
 * Created by xiaoyao9184 on 2016/12/4.
 */
public interface Config<This,Done> {

    This start(Done done);

    Done done();
}
