package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class ReNameConverter
        implements MetaContextBlockConverter<ReName> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ReName>()
                    .overall("RENAME")
                    .czse(d -> d.getDbName() != null)
                        .description("rename database")
                        .ref(ReNameDataBaseConverter.meta)
                        .data(d -> d)
                        .and()
                    .czse(d -> d.getTableName() != null)
                        .description("rename object")
                        .ref(ReNameTableConverter.meta)
                        .data(d -> d)
                        .and()
                    .subTakeLine()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(ReName context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class ReNameDataBaseConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ReName>()
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
                            .and()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }

    public static class ReNameTableConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ReName>()
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
                            .and()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }

}
