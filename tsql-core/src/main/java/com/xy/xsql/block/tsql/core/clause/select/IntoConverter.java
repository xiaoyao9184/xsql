package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.select.Having;
import com.xy.xsql.tsql.model.clause.select.Into;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class IntoConverter
        implements BlockConverter<Into> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Into> builder =
            new ReferenceBlockBuilder<Void,Into>()
                    .overall("INTO Clause")
                    .sub_keyword(Keywords.INTO)
                    .sub("new_table")
                        .data(Into::getNewTable)
                        .and()
                    .sub()
                        .optional()
                        .sub()
                            .keyword(Keywords.ON)
                            .and()
                        .sub("filegroup")
                            .data(Into::getFileGroup)
                            .and()
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Into into) {
        return builder
                .data(into)
                .build();
    }

}
