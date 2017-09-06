package com.xy.xsql.block.tsql.core.statement.ddl.create.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.MultipartNamesConverter;
import com.xy.xsql.block.tsql.core.element.column.ColumnDefinitionConverters;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.create.table.SimpleCreateTable;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class SimpleCreateTableConverter
        implements ModelMetaBlockConverter<SimpleCreateTable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SimpleCreateTable>()
                    .description("--Simple CREATE TABLE Syntax (common if not using options)")
                    .sub()
                        .description("create table")
                        .sub_keyword(Keywords.CREATE)
                        .sub_keyword(Keywords.TABLE)
                        .and()
                    .sub()
                        .sub()
                            .description("table name")
                            .ref(MultipartNamesConverter.TableNameConverter.meta)
                            .scope(SimpleCreateTable::getTableName)
                            .and()
                        .sub()
                            .description("column definition list")
                            .sub_keyword(Other.GROUP_START)
                            .sub("column definition list")
                                .list()
                                .ref(ColumnDefinitionConverters.DiskBased.meta)
                                .scope(SimpleCreateTable::getColumnDefinitionList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .syntax_indentation_right()
                    .syntax_sub_line()
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
