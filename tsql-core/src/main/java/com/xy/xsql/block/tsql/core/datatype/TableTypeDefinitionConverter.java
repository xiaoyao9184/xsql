package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.constraint.CheckConverters;
import com.xy.xsql.block.tsql.core.element.constraint.PrimaryUniqueConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableTypeDefinition;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TableTypeDefinitionConverter
        implements ModelMetaBlockConverter<TableTypeDefinition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,TableTypeDefinition>()
                    .overall("table_type_definition")
                    .sub_keyword(Keywords.TABLE)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .list()
                        .ref(ItemConverter.meta)
                        .scope(TableTypeDefinition::getList)
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ItemConverter
            implements ModelMetaBlockConverter<TableTypeDefinition.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableTypeDefinition.Item>()
                        .syntax_required()
                        .czse(d -> d instanceof ColumnDefinition)
                            .name("column_definition")
                            .ref(ColumnTypeDefinitionConverter.class)
                            .and()
                        .czse(d -> d instanceof TableConstraint)
                            .name("table_constraint")
                            .ref(TableConstraintConverter.class)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class TableConstraintConverter
            implements ModelMetaBlockConverter<TableConstraint> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableConstraint>()
                        .overall("table_constraint")
                        .czse(d -> d.getConstraint() instanceof PrimaryUnique)
                            .scope(TableConstraint::getConstraint)
                            .ref(PrimaryUniqueConverters.Simple.meta)
                            .and()
                        .czse(d -> d.getConstraint() instanceof Check)
                            .scope(TableConstraint::getConstraint)
                            .ref(CheckConverters.Simple.meta)
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
