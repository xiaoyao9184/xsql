package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.rowset.OpenDataSource;
import com.xy.xsql.tsql.model.functions.rowset.OpenJson;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class OpenJsonConverter
        implements ModelMetaBlockConverter<OpenJson> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,OpenJson>()
                    .overall("OPENJSON")
                    .sub_keyword(Function.Keywords.OPENJSON)
                    .sub_keyword(Other.GROUP_START)
                    .sub("jsonExpression")
                        .scope(d -> d.getJsonExpression())
                        .and()
                    .sub()
                        .optional(d -> d.getPath() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("path")
                            .scope(d -> d.getPath())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub("with_clause")
                        .optional(d -> d.getWith() == null)
                        .ref(OpenJsonConverter.metaWith)
                        .syntax_reference()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }



    // @formatter:off
    public static BlockMeta metaWith =
            new BlockMetaBuilder<Void,OpenJson>()
                    .overall("with_clause")
                    .sub_keyword(Keywords.WITH)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .list()
                        .ref(ItemConverter.meta)
                        .scope(d -> d.getWith())
                        .syntax_required()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on


    public static class ItemConverter
            implements ModelMetaBlockConverter<OpenJson.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,OpenJson.Item>()
                        .description("with_clause item")
                        .sub("colName")
                            .scope(d -> d.getColName())
                            .and()
                        .sub("type")
                            .scope(d -> d.getType())
                            .and()
                        .sub("column_path")
                            .optional(d -> d.getColumnPath() == null)
                            .scope(d -> d.getColumnPath())
                            .and()
                        .sub()
                            .optional(d -> !d.isUseAsJson())
                            .sub_keyword(Keywords.AS)
                            .sub_keyword(Keywords.Key.JSON)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }
    }
}
