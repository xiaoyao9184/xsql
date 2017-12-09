package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.core.configurator.Configurator;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.api.meta.TableMetaBuilder;

/**
 * Created by xiaoyao9184 on 2017/9/21.
 */
public interface EntityTableMetaBuilder<THIS,CFG,SRC,META extends TableMeta>
        extends Configurator<THIS,CFG>, TableMetaBuilder<SRC,META> {

//    boolean support(SRC source);
}
