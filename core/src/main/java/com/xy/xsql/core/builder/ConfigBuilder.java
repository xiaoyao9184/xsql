package com.xy.xsql.core.builder;

import com.xy.xsql.core.configurator.Configurator;

/**
 * Config Builder
 * Created by xiaoyao9184 on 2016/12/4.
 */
public interface ConfigBuilder<This, ConfigType, BuildSrcType, BuildTarType> extends
        BaseBuilder<BuildSrcType, BuildTarType>,
        Configurator<This, ConfigType> {

}
