package com.xy.xsql.orm.core;

/**
 * Configurator
 * Created by xiaoyao9184 on 2016/12/4.
 */
public interface Configurator<This, ConfigType> {

    /**
     * Set Config
     * @param config Config
     */
    This config(ConfigType config);

}
