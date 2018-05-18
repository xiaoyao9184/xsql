package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.constraint.CheckConverters;
import com.xy.xsql.block.tsql.core.element.constraint.NullOrNotNullConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.element.column.ColumnConstraint;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.constraint.Check;
import com.xy.xsql.tsql.model.element.constraint.NullOrNotNull;
import com.xy.xsql.tsql.model.element.constraint.PrimaryUnique;

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
                        .scope(d -> d.getName())
                        .and()
                    .sub("scalar_data_type")
                        .scope(d -> d.getDataType())
                        .and()
                    .sub()
                        .optional(d -> d.getCollationName() == null)
                        .sub_keyword(Keywords.COLLATE)
                        .sub("collation_definition")
                            .ref(ColumnConstraintConverter.class)
                            .scope(d -> d.getCollationName())
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
                    .sub()
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
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ColumnConstraintConverter
            implements ModelMetaBlockConverter<ColumnConstraint> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnConstraint>()
                        .overall("column_constraint")
                        .czse(d -> d.getConstraint() instanceof NullOrNotNull)
                            .syntax_optional()
                            .scope(ColumnConstraint::getConstraint)
                            .ref(NullOrNotNullConverter.meta)
                            .and()
                        .czse(d -> d.getConstraint() instanceof PrimaryUnique)
                            .syntax_optional()
                            .scope(ColumnConstraint::getConstraint)
                            .ref(SimplePrimaryUniqueConverter.meta)
                            .and()
                        .czse(d -> d.getConstraint() instanceof Check)
                            .scope(ColumnConstraint::getConstraint)
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

    public static class SimplePrimaryUniqueConverter
            implements ModelMetaBlockConverter<PrimaryUnique> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,PrimaryUnique>()
                        .czse(PrimaryUnique::isUsePrimaryKey)
                            .sub_keyword(Keywords.PRIMARY)
                            .sub_keyword(Keywords.KEY)
                            .and()
                        .czse_keyword(d -> !d.isUsePrimaryKey(), Keywords.UNIQUE)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
