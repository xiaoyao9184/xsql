package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class ReNameDatabaseConverter
        implements ReferenceBlockConverter<ReName> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,ReName> builder =
            new ReferenceBlockBuilder<Void,ReName>()
                    .overall("RENAME")
                    .sub_keyword(Keywords.Key.RENAME)
                    .sub_keyword(Keywords.DATABASE)
                    .sub("::")
                        .optional()
                        .data("::")
                        .and()
                    .sub("database_name")
                        .data(ReName::getDbName)
                        .and()
                    .sub_keyword(Keywords.TO)
                    .sub("new_database_name")
                        .data(ReName::getNewName)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(ReName reName) {
        return builder
                .data(reName)
                .build();
    }
}
