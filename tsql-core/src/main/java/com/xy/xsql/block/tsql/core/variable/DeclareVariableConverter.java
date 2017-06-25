package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.operator.Comparison;
import com.xy.xsql.tsql.model.variable.DeclareVariable;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class DeclareVariableConverter
        implements BlockConverter<DeclareVariable> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,DeclareVariable> builder =
            new ReferenceBlockBuilder<Void,DeclareVariable>()
                .overall("DECLARE @local_variable")
                .subTakeLine()
                .sub_keyword(Keywords.DECLARE)
                .sub()
                    .description("DECLARE's param")
                    .subTakeLine()
                    .czse(d -> d.getItems() != null)
                        .description("local&cursor_variable list")
                        .list(DeclareVariableItemPredicateRenderer.meta())
                        .oneMore()
                        .data(DeclareVariable::getItems)
                        .headFootTakeLine()
                        .and()
                    .czse(d -> d.getTableTypeDefinition() != null)
                        .description("table_variable_name")
                        .required()
                        .sub("@table_variable_name")
                            .data(DeclareVariable::getTableVariableName)
                            .and()
                        .sub("AS")
                            .keyword(Keywords.AS)
                            .optional()
                            .and()
                        .sub("table_type_definition")
                            .ref(TableTypeDefinition.class)
                            .data(DeclareVariable::getTableTypeDefinition)
                            .and()
                        .and()
                    .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert() {
        return builder.build();
    }

    @Override
    public Block convert(DeclareVariable declareVariable) {
        return builder
                .data(declareVariable)
                .build();
    }


    public static class DeclareVariableItemPredicateRenderer
            implements BlockConverter<DeclareVariable.Item> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,DeclareVariable.Item> builder =
                new ReferenceBlockBuilder<Void,DeclareVariable.Item>()
                        .description("local&cursor_variable")
                        .subTakeLine()
                        .czse(d -> d.getDataType() != null)
                            .description("@local_variable [AS] data_type  | [ = value ]")
                            .required()
                            .sub("@local_variable")
                                .data(DeclareVariable.Item::getLocalVariable)
                                .and()
                            .sub()
                                .optional()
                                .keyword(Keywords.AS)
                                .and()
                            .sub("data_type")
                                .data(DeclareVariable.Item::getDataType)
                                .and()
                            .sub()
                                .description("= value")
                                .optional()
                                .sub()
                                    .keyword(Comparison.EQUAL)
                                    .and()
                                .sub("value")
                                    .data(DeclareVariable.Item::getValue)
                                    .and()
                                .optional(d -> d.getValue() != null)
                                .and()
                            .and()
                        .czse(d -> d.getLocalVariable() != null)
                            .description("@cursor_variable_name CURSOR")
                            .required()
                            .sub("@cursor_variable_name")
                                .data(DeclareVariable.Item::getLocalVariable)
                                .and()
                            .sub()
                                .keyword(Keywords.CURSOR)
                                .and()
                            .and();
        // @formatter:on


        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(DeclareVariable.Item item) {
            return builder
                    .data(item)
                    .build();
        }
    }
}
