package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Top;
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
                        .list()
                        .sub()
                            .oneOf("column_definition")
                                .ref(ColumnTypeDefinitionConverter.class)
                                .and()
                            .oneOf("table_constraint")
                                .ref(TableConstraintConverter.class)
                                .and()
                            .and()
                        .data(TableTypeDefinition::getList)
                        .oneMore()
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


    public static class TableConstraintConverter
            implements BlockConverter<TableTypeDefinition.TableConstraint> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TableTypeDefinition.TableConstraint> builder =
                new ReferenceBlockBuilder<Void,TableTypeDefinition.TableConstraint>()
                        .overall("table_constraint")
                        .oneOf()
                            .sub()
                                .description("PRIMARY KEY | UNIQUE")
                                .required()
                                .oneOf()
                                    .filter(d -> d.isUsePrimaryKey())
                                    .sub_keyword(Keywords.PRIMARY)
                                    .sub_keyword(Keywords.KEY)
                                    .and()
                                .oneOf()
                                    .filter(d -> d.isUseUnique())
                                    .keyword(Keywords.UNIQUE)
                                    .and()
                                .and()
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .list("column_name")
                                .data(d -> d.getColumnName())
                                .more()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .oneOf()
                            .sub_keyword(Keywords.CHECK)
                            .sub_keyword(Other.GROUP_START)
                            .sub("logical_expression")
                                .data(d -> d.getLogicalExpression())
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
