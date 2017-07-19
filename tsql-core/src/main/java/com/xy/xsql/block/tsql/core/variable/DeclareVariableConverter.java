package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.datatype.TableTypeDefinitionConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Comparison;
import com.xy.xsql.tsql.model.variable.DeclareVariable;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class DeclareVariableConverter
        implements ModelMetaBlockConverter<DeclareVariable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DeclareVariable>()
                .overall("DECLARE @local_variable")
                .syntax_sub_line()
                .sub_keyword(Keywords.DECLARE)
                .sub()
                    .description("DECLARE's param")
                    .syntax_sub_line()
                    .czse(d -> d.getItems() != null)
                        .description("local/cursor list")
                        .list()
                        .ref(DeclareVariableItemPredicateRenderer.meta)
                        .scope(DeclareVariable::getItems)
                        .syntax_context_indentation()
                        .and()
                    .czse(d -> d.getTableTypeDefinition() != null)
                        .description("table")
                        .syntax_required()
                        .sub("@table_variable_name")
                            .scope(DeclareVariable::getTableVariableName)
                            .and()
                        .sub()
                            .optional(d -> !d.isUseAs())
                            .keyword(Keywords.AS)
                            .and()
                        .sub("table_type_definition")
                            .ref(TableTypeDefinitionConverter.class)
                            .scope(DeclareVariable::getTableTypeDefinition)
                            .and()
                        .and()
                    .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class DeclareVariableItemPredicateRenderer
            implements ModelMetaBlockConverter<DeclareVariable.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DeclareVariable.Item>()
                        .description("local/cursor")
                        .czse(d -> d.getDataType() != null)
                            .description("local")
                            .syntax_required()
                            .sub("@local_variable")
                                .scope(DeclareVariable.Item::getLocalVariable)
                                .and()
                            .sub()
                                .optional(d -> !d.isUseAs())
                                .keyword(Keywords.AS)
                                .and()
                            .sub("data_type")
                                .scope(DeclareVariable.Item::getDataType)
                                .and()
                            .sub()
                                .description("= value")
                                .optional(d -> d.getValue() == null)
                                .sub_keyword(Comparison.EQUAL)
                                .sub("value")
                                    .scope(DeclareVariable.Item::getValue)
                                    .and()
                                .and()
                            .and()
                        .czse(d -> d.getLocalVariable() != null)
                            .description("cursor")
                            .syntax_required()
                            .sub("@cursor_variable_name")
                                .scope(DeclareVariable.Item::getLocalVariable)
                                .and()
                            .sub_keyword(Keywords.CURSOR)
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
