package com.xy.xsql.tsql.converter.queries.hints;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableHintConverter
        implements ModelMetaBlockConverter<TableHint> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,TableHint>()
                    .overall("table_hint")
                    .sub()
                        .optional(d -> !d.isUseNOEXPAND())
                        .keyword(Keywords.Key.NOEXPAND)
                        .and()
                    .sub()
                        .czse(d ->
                                TableHint.Type.INDEX.equals(d.getType()) &&
                                !d.isUseOneIndexValue()
                        )
                            .description("index with list value")
                            .sub_keyword(Keywords.INDEX)
                            .sub_keyword(Other.GROUP_START)
                            .sub_list("index_value")
                                .scope(TableHint::getIndex_value)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d ->
                                TableHint.Type.INDEX.equals(d.getType()) &&
                                d.isUseOneIndexValue()
                        )
                            .sub_keyword(Keywords.INDEX)
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub_keyword(Other.GROUP_START)
                            .sub("index_value")
                                .scope(d -> d.getIndex_value().get(0))
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d -> TableHint.Type.FORCESEEK.equals(d.getType()))
                            .sub_keyword(TableHint.Type.FORCESEEK)
                            .sub()
                                .optional(d -> d.getIndex_value() == null ||
                                        d.getIndex_value().size() < 1)
                                .sub_keyword(Other.GROUP_START)
                                .sub("index_value")
                                    .scope(d -> d.getIndex_value().get(0))
                                    .and()
                                .sub_keyword(Other.GROUP_START)
                                .sub_list("index_column_name")
                                    .scope(TableHint::getIndex_column_name)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .czse_keyword(d -> TableHint.Type.FORCESCAN.equals(d.getType()),TableHint.Type.FORCESCAN)
                        .czse_keyword(d -> TableHint.Type.FORCESEEK.equals(d.getType()),TableHint.Type.FORCESEEK)
                        .czse_keyword(d -> TableHint.Type.HOLDLOCK.equals(d.getType()),TableHint.Type.HOLDLOCK)
                        .czse_keyword(d -> TableHint.Type.NOLOCK.equals(d.getType()),TableHint.Type.NOLOCK)
                        .czse_keyword(d -> TableHint.Type.NOWAIT.equals(d.getType()),TableHint.Type.NOWAIT)
                        .czse_keyword(d -> TableHint.Type.PAGLOCK.equals(d.getType()),TableHint.Type.PAGLOCK)
                        .czse_keyword(d -> TableHint.Type.READCOMMITTED.equals(d.getType()),TableHint.Type.READCOMMITTED)
                        .czse_keyword(d -> TableHint.Type.READCOMMITTEDLOCK.equals(d.getType()),TableHint.Type.READCOMMITTEDLOCK)
                        .czse_keyword(d -> TableHint.Type.READPAST.equals(d.getType()),TableHint.Type.READPAST)
                        .czse_keyword(d -> TableHint.Type.READUNCOMMITTED.equals(d.getType()),TableHint.Type.READUNCOMMITTED)
                        .czse_keyword(d -> TableHint.Type.REPEATABLEREAD.equals(d.getType()),TableHint.Type.REPEATABLEREAD)
                        .czse_keyword(d -> TableHint.Type.ROWLOCK.equals(d.getType()),TableHint.Type.ROWLOCK)
                        .czse_keyword(d -> TableHint.Type.SERIALIZABLE.equals(d.getType()),TableHint.Type.SERIALIZABLE)
                        .czse_keyword(d -> TableHint.Type.SNAPSHOT.equals(d.getType()),TableHint.Type.SNAPSHOT)
                        .czse_keyword(d -> TableHint.Type.SNAPSHOT.equals(d.getType()),TableHint.Type.SNAPSHOT)
                        .czse(d -> TableHint.Type.SPATIAL_WINDOW_MAX_CELLS.equals(d.getType()))
                            .sub_keyword(TableHint.Type.SPATIAL_WINDOW_MAX_CELLS)
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub("integer")
                                .scope(TableHint::getInteger)
                                .and()
                            .and()
                        .czse_keyword(d -> TableHint.Type.TABLOCK.equals(d.getType()), TableHint.Type.TABLOCK)
                        .czse_keyword(d -> TableHint.Type.TABLOCKX.equals(d.getType()), TableHint.Type.TABLOCKX)
                        .czse_keyword(d -> TableHint.Type.UPDLOCK.equals(d.getType()), TableHint.Type.UPDLOCK)
                        .czse_keyword(d -> TableHint.Type.XLOCK.equals(d.getType()), TableHint.Type.XLOCK)
                        .syntax_required()
                        .syntax_context_indentation()
                        .syntax_sub_line()
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
