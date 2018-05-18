package com.xy.xsql.block.tsql.core.statement.ddl.create;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statements.create.CreateDataBase;

/**
 * Created by xiaoyao9184 on 2017/8/3.
 */
public class CreateDataBaseConverter
        implements ModelMetaBlockConverter<CreateDataBase> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CreateDataBase>()
                    .overall("Create a database")
                    .sub()
                        .description("create database")
                        .sub_keyword(Keywords.CREATE)
                        .sub_keyword(Keywords.DATABASE)
                        .sub("database_name")
                            .scope(d -> d.getDatabaseName())
                            .and()
                        .and()
                    .sub()
                        .description("containment none/partial")
                        .optional(d -> !d.isUseContainmentNone() && !d.isUseContainmentPartial())
                        .sub_keyword(Keywords.Key.CONTAINMENT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .description("none/partial")
                            .czse_keyword(d -> d.isUseContainmentNone(),Keywords.Key.NONE)
                            .czse_keyword(d -> d.isUseContainmentPartial(),Keywords.Key.PARTIAL)
                            .syntax_required()
                            .and()
                        .and()
//                    .sub()
//                        .description("on")
//                        .optional(d -> !d.isUseOn())
//                        .sub()
//                            .description("primary")
//                            .optional(d -> !d.isUseOn())
//                            .and()
//                        .sub()
//                            .description("filegroup")
//                            .optional(d -> !d.isUseOn())
//                            .and()
//                        .sub()
//                            .description("log on")
//                            .optional(d -> !d.isUseOn())
//                            .and()
//                        .and()
//                    .sub()
//                        .description("collation_name")
//                        .optional(d -> !d.isUseOn())
//                        .and()
//                    .sub()
//                        .description("with")
//                        .optional(d -> !d.isUseOn())
//                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
