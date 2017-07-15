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
                .style_sub_line_delimiter()
                .sub_keyword(Keywords.DECLARE)
                .sub()
                    .description("DECLARE's param")
                    .style_sub_line_delimiter()
                    .czse(d -> d.getItems() != null)
                        .description("local/cursor list")
                        .list()
                        .ref(DeclareVariableItemPredicateRenderer.meta)
                        .scope(DeclareVariable::getItems)
                        .style_convention_line_delimiter()
                        .and()
                    .czse(d -> d.getTableTypeDefinition() != null)
                        .description("table")
                        .style_required()
                        .sub("@table_variable_name")
                            .scope(DeclareVariable::getTableVariableName)
                            .and()
                        .sub("AS")
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
                        .style_sub_line_delimiter()
                        .czse(d -> d.getDataType() != null)
                            .description("local")
                            .style_required()
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
                                .sub()
                                    .keyword(Comparison.EQUAL)
                                    .and()
                                .sub("value")
                                    .scope(DeclareVariable.Item::getValue)
                                    .and()
                                .and()
                            .and()
                        .czse(d -> d.getLocalVariable() != null)
                            .description("cursor")
                            .style_required()
                            .sub("@cursor_variable_name")
                                .scope(DeclareVariable.Item::getLocalVariable)
                                .and()
                            .sub()
                                .keyword(Keywords.CURSOR)
                                .and()
                            .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
        return meta;
    }

    }
}
