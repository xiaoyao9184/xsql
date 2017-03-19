package com.xy.xsql.core.configurator;

/**
 * ConfigInOut
 * Created by xiaoyao9184 on 2016/12/4.
 */
public interface ConfigInOut<This,Done> {

    This in(Done done);

    Done out();
}
