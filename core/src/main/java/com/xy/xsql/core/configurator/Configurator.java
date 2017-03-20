package com.xy.xsql.core.configurator;

/**
 * Configurator
 * Config Target in implement Class
 * Created by xiaoyao9184 on 2016/12/4.
 * @param <This>
 * @param <Target>
 */
public interface Configurator<This, Target> {

    /**
     * Config Target
     * @param target Configured Target
     */
    This config(Target target);

}
