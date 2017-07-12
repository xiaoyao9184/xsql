package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Into;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class IntoConverter
        implements ModelMetaBlockConverter<Into> {

    // @formatter:off
    public static BlockMeta meta =
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
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
