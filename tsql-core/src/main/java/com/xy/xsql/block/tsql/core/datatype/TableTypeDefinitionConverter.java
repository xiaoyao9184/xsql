package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TableTypeDefinitionConverter
        implements BlockConverter<TableTypeDefinition> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,TableTypeDefinition> builder =
            new ReferenceBlockBuilder<Void,TableTypeDefinition>()
                    .overall("table_type_definition")
                    .sub_keyword(Keywords.TABLE)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .list(ItemConverter.meta())
                        .data(TableTypeDefinition::getList)
                        .more()
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(TableTypeDefinition tableTypeDefinition) {
        return builder
                .data(tableTypeDefinition)
                .build();
    }


    public static class ItemConverter
            implements BlockConverter<TableTypeDefinition.Item> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TableTypeDefinition.Item> builder =
                new ReferenceBlockBuilder<Void,TableTypeDefinition.Item>()
                        .required()
                        .czse(d -> d instanceof ColumnDefinition)
                            .name("column_definition")
                            .ref(ColumnTypeDefinitionConverter.class)
                            .and()
                        .czse(d -> d instanceof TableTypeDefinition.TableConstraint)
                            .name("table_constraint")
                            .ref(TableConstraintConverter.class)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(TableTypeDefinition.Item item) {
            return builder
                    .data(item)
                    .build();
        }

    }

    public static class TableConstraintConverter
            implements BlockConverter<TableTypeDefinition.TableConstraint> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TableTypeDefinition.TableConstraint> builder =
                new ReferenceBlockBuilder<Void,TableTypeDefinition.TableConstraint>()
                        .overall("table_constraint")
                        .czse(d -> d.getColumnName() != null)
                            .sub()
                                .description("PRIMARY KEY | UNIQUE")
                                .required()
                                .czse(TableTypeDefinition.TableConstraint::isUsePrimaryKey)
                                    .sub_keyword(Keywords.PRIMARY)
                                    .sub_keyword(Keywords.KEY)
                                    .and()
                                .czse(TableTypeDefinition.TableConstraint::isUseUnique)
                                    .keyword(Keywords.UNIQUE)
                                    .and()
                                .and()
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .list("column_name")
                                .data(TableTypeDefinition.TableConstraint::getColumnName)
                                .more()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d -> d.getLogicalExpression() != null)
                            .sub_keyword(Keywords.CHECK)
                            .sub_keyword(Other.GROUP_START)
                            .sub("logical_expression")
                                .data(TableTypeDefinition.TableConstraint::getLogicalExpression)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(TableTypeDefinition.TableConstraint tableConstraint) {
            return builder
                    .data(tableConstraint)
                    .build();
        }

    }

}
