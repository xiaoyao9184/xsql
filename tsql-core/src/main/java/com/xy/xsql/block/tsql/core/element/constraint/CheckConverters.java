package com.xy.xsql.block.tsql.core.element.constraint;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.constraint.Check;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class CheckConverters {

    public static class Simple {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Check>()
                        .sub_keyword(Keywords.CHECK)
                        .sub_keyword(Other.GROUP_START)
                        .sub("logical_expression")
                            .scope(Check::getLogicalExpression)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on
    }

    public static class Replication {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Check>()
                        .sub_keyword(Keywords.CHECK)
                        .sub()
                            .description("not for replication")
                            .optional(d -> !d.isUseNotForReplication())
                            .sub_keyword(Keywords.NOT)
                            .sub_keyword(Keywords.FOR)
                            .sub_keyword(Keywords.REPLICATION)
                            .and()
                        .sub_keyword(Other.GROUP_START)
                        .sub("logical_expression")
                            .scope(Check::getLogicalExpression)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on
    }

}
