package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class ColumnTypeDefinitionConverter
        implements ReferenceBlockConverter<ColumnDefinition> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,ColumnDefinition> builder =
            new ReferenceBlockBuilder<Void,ColumnDefinition>()
                    .overall("column_definition")
                    .sub("column_name")
                        .data(d -> d.getName())
                        .and()
                    .sub("scalar_data_type")
                        .data(d -> d.getDataType())
                        .and()
                    .sub()
                        .optional()
                        .sub_keyword(Keywords.COLLATE)
                        .sub("collation_definition")
                            .ref(ColumnConstraintConverter.class)
                            .data(d -> d.getCollationName())
                            .and()
                        .and()
//                    .sub_meta()
//                        .optional()
//                        .czse_meta()
//                            .optional()
//                            .sub_keyword(Keywords.DEFAULT)
//                            .sub_meta("constant_expression")
//                                .data(null)
//                                .and()
//                            .and()
//                        .czse_meta()
//                            .optional()
//                            .sub_keyword(Keywords.IDENTITY)
//                            .sub_meta("constant_expression")
//                                .optional()
//                                .sub_keyword(Other.GROUP_START)
//                                .sub_meta()
//                                    .list()
//                                    .sub_meta("seed")
//                                        .data(d -> d.getSeed())
//                                        .and()
//                                    .sub_meta("increment")
//                                        .data(d -> d.getIncrement())
//                                        .and()
//                                    .and()
//                                .sub_keyword(Other.GROUP_END)
//                                .and()
//                            .and()
//                        .and()
                    .sub("ROWGUIDCOL")
                        .optional()
                        .keyword(Keywords.ROWGUIDCOL)
                        .and()
                    .sub()
                        .list()
                        .sub("column_constraint")
                            .optional()
                            .and()
                        .data(d -> d.getCollationName())
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(ColumnDefinition columnDefinition) {
        return builder
                .data(columnDefinition)
                .build();
    }


    public static class ColumnConstraintConverter
            implements ReferenceBlockConverter<ColumnDefinition.ColumnConstraint> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,ColumnDefinition.ColumnConstraint> builder =
                new ReferenceBlockBuilder<Void,ColumnDefinition.ColumnConstraint>()
                        .overall("column_constraint")
                        .czse(d -> d.isUseNull() || d.isUseNotNull())
                            .optional()
                            .czse(ColumnDefinition.ColumnConstraint::isUseNull)
                                .keyword(Keywords.NULL)
                                .and()
                            .czse(ColumnDefinition.ColumnConstraint::isUseNotNull)
                                .sub_keyword(Keywords.NOT)
                                .sub_keyword(Keywords.NULL)
                                .and()
                            .and()
                        .czse(d -> d.isUsePrimaryKey() || d.isUseUique())
                            .optional()
                            .czse(ColumnDefinition.ColumnConstraint::isUsePrimaryKey)
                                .sub_keyword(Keywords.PRIMARY)
                                .sub_keyword(Keywords.KEY)
                                .and()
                            .czse(ColumnDefinition.ColumnConstraint::isUseUique)
                                .keyword(Keywords.UNIQUE)
                                .and()
                            .and()
                        .czse(d -> d.getLogicalExpression() != null)
                            .sub_keyword(Keywords.CHECK)
                            .sub_keyword(Other.GROUP_START)
                            .sub("logical_expression")
                                .data(ColumnDefinition.ColumnConstraint::getLogicalExpression)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(ColumnDefinition.ColumnConstraint columnConstraint) {
            return builder
                    .data(columnConstraint)
                    .build();
        }

    }

}
