package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cursor.Cursor_Status;
import com.xy.xsql.tsql.model.functions.datatype.DataLength;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DataLengthConverter
        implements ModelMetaBlockConverter<DataLength> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DataLength>()
                    .overall("DATALENGTH")
                    .sub_keyword(Function.Keywords.DATALENGTH)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
