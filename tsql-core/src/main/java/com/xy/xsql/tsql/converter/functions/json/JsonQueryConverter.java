package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.json.Json_Query;
import com.xy.xsql.tsql.model.functions.json.Json_Value;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class JsonQueryConverter
        implements ModelMetaBlockConverter<Json_Query> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Json_Query>()
                    .overall("JSON_QUERY")
                    .sub_keyword(Function.Keywords.JSON_QUERY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub()
                        .optional(d -> d.getPath() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("path")
                            .scope(d -> e_string(d.getPath()))
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
