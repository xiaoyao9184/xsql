package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.element.Other;

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
                        .czse(d -> d instanceof TableTypeDefinition.TableConstraint)
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
            implements ModelMetaBlockConverter<TableTypeDefinition.TableConstraint> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableTypeDefinition.TableConstraint>()
                        .overall("table_constraint")
                        .czse(d -> d.getColumnName() != null)
                            .sub()
                                .description("primary key/unique")
                                .syntax_required()
                                .czse(TableTypeDefinition.TableConstraint::isUsePrimaryKey)
                                    .sub_keyword(Keywords.PRIMARY)
                                    .sub_keyword(Keywords.KEY)
                                    .and()
                                .czse_keyword(TableTypeDefinition.TableConstraint::isUseUnique, Keywords.UNIQUE)
                                .and()
                            .sub_keyword(Other.GROUP_START)
                            .sub("column_name")
                                .list()
                                .scope(TableTypeDefinition.TableConstraint::getColumnName)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d -> d.getLogicalExpression() != null)
                            .sub_keyword(Keywords.CHECK)
                            .sub_keyword(Other.GROUP_START)
                            .sub("logical_expression")
                                .scope(TableTypeDefinition.TableConstraint::getLogicalExpression)
                                .and()
                            .sub_keyword(Other.GROUP_END)
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
