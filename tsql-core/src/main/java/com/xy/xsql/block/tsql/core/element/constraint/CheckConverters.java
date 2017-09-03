package com.xy.xsql.block.tsql.core.element.constraint;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.constraint.Check;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class CheckConverters {

    public static class SimpleCheckConverter
            implements ModelMetaBlockConverter<Check> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Check>()
                        .overall("table_type_definition")
                        .sub_keyword(Keywords.CHECK)
                        .sub_keyword(Other.GROUP_START)
                        .sub("logical_expression")
                            .scope(Check::getLogicalExpression)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
