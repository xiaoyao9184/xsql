package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.json.IsJson;
import com.xy.xsql.tsql.model.functions.json.Json_Value;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class JsonValueConverter
        implements ModelMetaBlockConverter<Json_Value> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Json_Value>()
                    .overall("JSON_VALUE")
                    .sub_keyword(Function.Keywords.JSON_VALUE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("path")
                        .scope(d -> c_string(d.getPath()))
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
