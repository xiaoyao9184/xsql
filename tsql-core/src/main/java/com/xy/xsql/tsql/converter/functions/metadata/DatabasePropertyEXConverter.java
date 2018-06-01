package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.ColumnProperty;
import com.xy.xsql.tsql.model.functions.metadata.DatabasePropertyEX;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DatabasePropertyEXConverter
        implements ModelMetaBlockConverter<DatabasePropertyEX> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DatabasePropertyEX>()
                    .overall("DATABASEPROPERTYEX")
                    .sub_keyword(Function.Keywords.DATABASEPROPERTYEX)
                    .sub_keyword(Other.GROUP_START)
                    .sub("database")
                        .scope(d -> d.getDatabase())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("property")
                        .scope(d -> d.getProperty())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
