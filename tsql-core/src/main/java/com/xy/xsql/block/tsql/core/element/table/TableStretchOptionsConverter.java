package com.xy.xsql.block.tsql.core.element.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;
import com.xy.xsql.tsql.model.datatypes.table.table.TableStretchOptions;
import com.xy.xsql.tsql.model.elements.operators.Assignment;

import static com.xy.xsql.block.tsql.core.element.EnumConverterUtil.getSyntaxString;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class TableStretchOptionsConverter
        implements ModelMetaBlockConverter<TableStretchOptions> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,TableStretchOptions>()
                    .overall("table_stretch_options")
                    .syntax_required()
                    .syntax_context_indentation()
                    .sub()
                        .description("constraint name")
                        .optional(d -> !d.isUseFilterPredicate())
                        .sub_keyword(TableOption.KeyWords.FILTER_PREDICATE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse(d -> d.getTablePredicateFunction() == null,"null")
                                .scope(d -> "null")
                                .and()
                            .czse(d -> d.getTablePredicateFunction() != null,"table_predicate_function")
                                .scope(d -> d.getTablePredicateFunction())
                                .and()
                            .syntax_required()
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .and()
                    .sub()
                        .description("constraint")
                        .sub_keyword(TableOption.KeyWords.MIGRATION_STATE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub(getSyntaxString(TableStretchOptions.MigrationState.class))
                            .scope(d -> d.getMigrationState())
                            .and()
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
