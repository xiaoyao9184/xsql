package com.xy.xsql.block.tsql.core.element.constraint;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;

/**
 * Created by xiaoyao9184 on 2017/9/5.
 */
public class ColumnNameConverters {

    public static class Base {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,PrimaryUnique.Column>()
                        .description("constraint column name")
                        .sub("column")
                            .scope(d -> d.getColumn())
                            .and()
                        .build();
        // @formatter:on

    }

    public static class Order {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,PrimaryUnique.Column>()
                        .description("constraint column name")
                        .sub("column")
                            .scope(d -> d.getColumn())
                            .and()
                        .sub()
                            .optional(d -> d.isUseAsc() == null)
                            .czse_keyword(d -> d.isUseAsc(),Keywords.ASC)
                            .czse_keyword(d -> !d.isUseAsc(),Keywords.DESC)
                            .and()
                        .build();
        // @formatter:on
    }

}
