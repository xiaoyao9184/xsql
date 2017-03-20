package com.xy.xsql.core.builder;


import com.xy.xsql.core.configurator.Configurator;

/**
 * Config Builder
 * Created by xiaoyao9184 on 2016/12/4.
 * @param <This>
 * @param <ConfigType>
 * @param <Source>
 * @param <Target>
 */
@Deprecated
public interface ConfigBuilder<This, ConfigType, Source, Target>
        extends
        BaseBuilder<Source, Target>,
        Configurator<This, ConfigType> {

}
