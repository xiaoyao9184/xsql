package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.ranking.Dense_Rank;
import com.xy.xsql.tsql.model.functions.rowset.OpenDataSource;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class OpenDataSourceConverter
        implements ModelMetaBlockConverter<OpenDataSource> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,OpenDataSource>()
                    .overall("OPENDATASOURCE")
                    .sub_keyword(Function.Keywords.OPENDATASOURCE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("provider_name")
                        .scope(d -> d.getProviderName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("init_string")
                        .scope(d -> d.getInitString())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
