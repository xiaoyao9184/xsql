package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Col_Length;
import com.xy.xsql.tsql.model.functions.metadata.Col_Name;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ColNameConverter
        implements ModelMetaBlockConverter<Col_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Col_Name>()
                    .overall("COL_NAME")
                    .sub_keyword(Function.Keywords.COL_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("table_id")
                        .scope(d -> d.getTableId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("column_id")
                        .scope(d -> d.getColumnId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
