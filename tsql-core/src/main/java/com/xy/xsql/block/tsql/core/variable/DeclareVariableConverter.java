package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.block.tsql.core.datatype.TableTypeDefinitionConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Comparison;
import com.xy.xsql.tsql.model.variable.DeclareVariable;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class DeclareVariableConverter
        implements MetaContextBlockConverter<DeclareVariable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DeclareVariable>()
                .overall("DECLARE @local_variable")
                .subTakeLine()
                .sub_keyword(Keywords.DECLARE)
                .sub()
                    .description("DECLARE's param")
                    .subTakeLine()
                    .czse(d -> d.getItems() != null)
                        .description("local/cursor list")
                        .list()
                        .ref(DeclareVariableItemPredicateRenderer.meta)
                        .data(DeclareVariable::getItems)
                        .headFootTakeLine()
                        .and()
                    .czse(d -> d.getTableTypeDefinition() != null)
                        .description("table")
                        .required()
                        .sub("@table_variable_name")
                            .data(DeclareVariable::getTableVariableName)
                            .and()
                        .sub("AS")
                            .optional(d -> !d.isUseAs())
                            .keyword(Keywords.AS)
                            .and()
                        .sub("table_type_definition")
                            .ref(TableTypeDefinitionConverter.class)
                            .data(DeclareVariable::getTableTypeDefinition)
                            .and()
                        .and()
                    .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(DeclareVariable context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class DeclareVariableItemPredicateRenderer
            implements MetaContextBlockConverter<DeclareVariable.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DeclareVariable.Item>()
                        .description("local/cursor")
                        .subTakeLine()
                        .czse(d -> d.getDataType() != null)
                            .description("local")
                            .required()
                            .sub("@local_variable")
                                .data(DeclareVariable.Item::getLocalVariable)
                                .and()
                            .sub()
                                .optional(d -> !d.isUseAs())
                                .keyword(Keywords.AS)
                                .and()
                            .sub("data_type")
                                .data(DeclareVariable.Item::getDataType)
                                .and()
                            .sub()
                                .description("= value")
                                .optional(d -> d.getValue() == null)
                                .sub()
                                    .keyword(Comparison.EQUAL)
                                    .and()
                                .sub("value")
                                    .data(DeclareVariable.Item::getValue)
                                    .and()
                                .and()
                            .and()
                        .czse(d -> d.getLocalVariable() != null)
                            .description("cursor")
                            .required()
                            .sub("@cursor_variable_name")
                                .data(DeclareVariable.Item::getLocalVariable)
                                .and()
                            .sub()
                                .keyword(Keywords.CURSOR)
                                .and()
                            .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

        @Override
        public MetaContextBlock convert(DeclareVariable.Item context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }
}
