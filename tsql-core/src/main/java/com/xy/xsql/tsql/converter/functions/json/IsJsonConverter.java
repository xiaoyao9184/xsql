package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datatype.DataLength;
import com.xy.xsql.tsql.model.functions.json.IsJson;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsJsonConverter
        implements ModelMetaBlockConverter<IsJson> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IsJson>()
                    .overall("ISJSON")
                    .sub_keyword(Function.Keywords.ISJSON)
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
