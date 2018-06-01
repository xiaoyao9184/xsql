package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.rowset.OpenDataSource;
import com.xy.xsql.tsql.model.functions.rowset.OpenQuery;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class OpenQueryConverter
        implements ModelMetaBlockConverter<OpenQuery> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,OpenQuery>()
                    .overall("OPENQUERY")
                    .sub_keyword(Function.Keywords.OPENQUERY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("linked_server")
                        .scope(d -> d.getLinkedServer())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'query'")
                        .scope(d -> d.getQuery())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
