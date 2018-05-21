package com.xy.xsql.tsql.converter.datatypes.table.column;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.column.ComputedColumnDefinition;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class ComputedColumnDefinitionConverter
        implements ModelMetaBlockConverter<ComputedColumnDefinition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ComputedColumnDefinition>()
                    .overall("computed_column_definition")
                    .sub()
                        .description("column as expression")
                        .sub("column_name")
                            .scope(ComputedColumnDefinition::getName)
                            .and()
                        .sub_keyword(Keywords.AS)
                        .sub("computed_column_expression")
                            .scope(d -> d.getComputedColumnExpression())
                            .and()
                        .and()
                    .sub()
                        .description("persisted not null")
                        .optional(d -> d.getPersistedNotNull() == null)
                        .sub_keyword(Keywords.Key.PERSISTED)
                        .sub()
                            .description("not null")
                            .optional(d -> !d.getPersistedNotNull())
                            .sub_keyword(Keywords.NOT)
                            .sub_keyword(Keywords.NULL)
                            .and()
                        .and()
                    .sub()
                        .description("constraint")
                        .optional(d -> d.getConstraint() == null)
                        .ref(ColumnConstraintConverters.DiskBased.meta)
                        .scope(d -> d.getConstraint())
                        .syntax_context_indentation()
                        .and()
                    .syntax_sub_line()
                    .sub_format_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
