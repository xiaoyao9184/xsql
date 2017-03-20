package com.xy.xsql.core.configurator;

/**
 * BaseConfigurator
 * Config Target in implement Class
 * Created by xiaoyao9184 on 2017/3/19.
 * @param <Target>
 */
public interface BaseConfigurator<Target> {

    /**
     * Config Target
     * @param target Configured Target
     */
    void config(Target target);
}
