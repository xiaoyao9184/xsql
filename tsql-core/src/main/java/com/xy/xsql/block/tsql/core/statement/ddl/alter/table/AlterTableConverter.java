package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.MultipartNamesConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.DataType;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.*;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterTableConverter
        implements ModelMetaBlockConverter<AlterTable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterTable>()
                    .description("-- Syntax for SQL Server and Azure SQL Database")
                    .sub()
                        .sub_keyword(Keywords.ALTER)
                        .sub_keyword(Keywords.TABLE)
                        .sub()
                            .description("table_name")
                            .scope(d -> d.getTableName())
                            .ref(MultipartNamesConverter.TableNameConverter.meta)
                            .and()
                        .and()
                    .sub()
                        .czse(d -> d.getItem() instanceof AlterColumn)
                            .ref(AlterColumnConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof Add)
                            .ref(AddConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof Drop)
                            .ref(DropConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof AlterConstraint)
                            .ref(AlterConstraintConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof AlterTrigger)
                            .ref(AlterTriggerConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof AlterChangeTracking)
                            .ref(AlterChangeTrackingConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof AlterPartition)
                            .ref(AlterPartitionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof AlterPartition)
                            .ref(AlterPartitionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof Set)
                            .ref(SetConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof Rebuild)
                            .ref(RebuildConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof AlterTableOption,"table_option")
                            .ref(AlterTableOptionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof AlterFiletableOption,"filetable_option")
                            .ref(AlterFiletableOptionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getItem() instanceof StretchConfiguration,"stretch_configuration")
                            .ref(StretchConfigurationConverter.meta)
                            .scope(d -> d)
                            .and()
                        .syntax_required()
                        .syntax_context_indentation()
//                        .syntax_sub_line()
                        .sub_syntax()
                            .line()
                            .indentation(0)
                            .delimiter("\n\t| ")
                            .and()
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }





}
