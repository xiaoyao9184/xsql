package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Into;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class IntoConverter
        implements MetaContextBlockConverter<Into> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Into> builder =
            new BlockMetaBuilder<Void,Into>()
                    .overall("INTO Clause")
                    .sub_keyword(Keywords.INTO)
                    .sub("new_table")
                        .data(Into::getNewTable)
                        .and()
                    .sub()
                        .description("on filegroup")
                        .optional(d -> d.getFileGroup() == null)
                        .sub()
                            .keyword(Keywords.ON)
                            .and()
                        .sub("filegroup")
                            .data(Into::getFileGroup)
                            .and()
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(Into context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
