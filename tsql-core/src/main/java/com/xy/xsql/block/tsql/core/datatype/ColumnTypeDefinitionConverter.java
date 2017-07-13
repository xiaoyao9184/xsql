package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class ColumnTypeDefinitionConverter
        implements ModelMetaBlockConverter<ColumnDefinition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ColumnDefinition>()
                    .overall("column_definition")
                    .sub("column_name")
                        .data(d -> d.getName())
                        .and()
                    .sub("scalar_data_type")
                        .data(d -> d.getDataType())
                        .and()
                    .sub()
                        .optional(d -> d.getCollationName() == null)
                        .sub_keyword(Keywords.COLLATE)
                        .sub("collation_definition")
                            .ref(ColumnConstraintConverter.class)
                            .data(d -> d.getCollationName())
                            .and()
                        .and()
//                    .sub_meta()
//                        .optional()
//                        .czse_ref()
//                            .optional()
//                            .sub_keyword(Keywords.DEFAULT)
//                            .sub_meta("constant_expression")
//                                .data(null)
//                                .and()
//                            .and()
//                        .czse_ref()
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
                        .optional(d -> true)
                        .keyword(Keywords.ROWGUIDCOL)
                        .and()
//                    .sub()
//                        .list()
//                        .sub("column_constraint")
//                            .optional()
//                            .and()
//                        .data(d -> d.getCollationName())
//                        .and()
                    .subTakeLine()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ColumnConstraintConverter
            implements ModelMetaBlockConverter<ColumnDefinition.ColumnConstraint> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnDefinition.ColumnConstraint>()
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
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
