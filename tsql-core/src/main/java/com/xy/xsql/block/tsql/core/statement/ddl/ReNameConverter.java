package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class ReNameConverter
        implements ReferenceBlockConverter<ReName> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,ReName> builder =
            new ReferenceBlockBuilder<Void,ReName>()
                    .overall("RENAME")
                    .czse(d -> d.getDbName() != null)
                        .description("rename database")
                        .ref(ReNameDataBaseConverter.meta())
                        .data(d -> d)
                        .and()
                    .czse(d -> d.getTableName() != null)
                        .description("rename object")
                        .ref(ReNameTableConverter.meta())
                        .data(d -> d)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(ReName reName) {
        return builder
                .data(reName)
                .build();
    }

    public static class ReNameDataBaseConverter {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,ReName> builder =
                new ReferenceBlockBuilder<Void,ReName>()
                        .overall("RENAME")
                        .sub_keyword(Keywords.Key.RENAME)
                        .sub_keyword(Keywords.DATABASE)
                        .sub("::")
                            .optional(d -> true)
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

        public static BlockMeta meta() {
            return builder.build();
        }

    }

    public static class ReNameTableConverter {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,ReName> builder =
                new ReferenceBlockBuilder<Void,ReName>()
                        .overall("RENAME")
                        .sub_keyword(Keywords.Key.RENAME)
                        .sub_keyword(Keywords.Key.OBJECT)
                        .sub("::")
                            .optional(d -> true)
                            .data("::")
                            .and()
                        .sub("[ [ database_name .  [schema_name ] ] . ] | [schema_name . ] ] table_name")
                            .data(ReName::getTableName)
                            .and()
                        .sub_keyword(Keywords.TO)
                        .sub("new_table_name")
                            .data(ReName::getNewName)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

    }

}
