package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;

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
                        .required()
                        .czse(d ->
                                TableHint.Type.INDEX.equals(d.getType()) &&
                                !d.isUseOneIndexValue()
                        )
                            .description("INDEX ( index_value [ ,...n ] )")
                            .sub_keyword(Keywords.INDEX)
                            .sub_keyword(Other.GROUP_START)
                            .sub_list("index_value")
                                .data(TableHint::getIndex_value)
                                .and()
//                            .sub()
//                                .list("index_value")
//                                .more()
//                                .data(TableHint::getIndex_value)
//                                .and()
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
                                .data(d -> d.getIndex_value().get(0))
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d -> TableHint.Type.FORCESEEK.equals(d.getType()))
                            .sub()
                                .keyword(TableHint.Type.FORCESEEK)
                                .and()
                            .sub()
                                .optional(d -> d.getIndex_value() == null ||
                                        d.getIndex_value().size() < 1)
                                .sub_keyword(Other.GROUP_START)
                                .sub("index_value")
                                    .data(d -> d.getIndex_value().get(0))
                                    .and()
                                .sub_keyword(Other.GROUP_START)
                                .sub_list("index_column_name")
                                    .data(TableHint::getIndex_column_name)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .czse(d -> TableHint.Type.FORCESCAN.equals(d.getType()))
                            .keyword(TableHint.Type.FORCESCAN)
                            .and()
                        .czse(d -> TableHint.Type.FORCESEEK.equals(d.getType()))
                            .keyword(TableHint.Type.FORCESEEK)
                            .and()
                        .czse(d -> TableHint.Type.HOLDLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.HOLDLOCK)
                            .and()
                        .czse(d -> TableHint.Type.NOLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.NOLOCK)
                            .and()
                        .czse(d -> TableHint.Type.NOWAIT.equals(d.getType()))
                            .keyword(TableHint.Type.NOWAIT)
                            .and()
                        .czse(d -> TableHint.Type.PAGLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.PAGLOCK)
                            .and()
                        .czse(d -> TableHint.Type.READCOMMITTED.equals(d.getType()))
                            .keyword(TableHint.Type.READCOMMITTED)
                            .and()
                        .czse(d -> TableHint.Type.READCOMMITTEDLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.READCOMMITTEDLOCK)
                            .and()
                        .czse(d -> TableHint.Type.READPAST.equals(d.getType()))
                            .keyword(TableHint.Type.READPAST)
                            .and()
                        .czse(d -> TableHint.Type.READUNCOMMITTED.equals(d.getType()))
                            .keyword(TableHint.Type.READUNCOMMITTED)
                            .and()
                        .czse(d -> TableHint.Type.REPEATABLEREAD.equals(d.getType()))
                            .keyword(TableHint.Type.REPEATABLEREAD)
                            .and()
                        .czse(d -> TableHint.Type.ROWLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.ROWLOCK)
                            .and()
                        .czse(d -> TableHint.Type.SERIALIZABLE.equals(d.getType()))
                            .keyword(TableHint.Type.SERIALIZABLE)
                            .and()
                        .czse(d -> TableHint.Type.SNAPSHOT.equals(d.getType()))
                            .keyword(TableHint.Type.SNAPSHOT)
                            .and()
                        .czse(d -> TableHint.Type.SNAPSHOT.equals(d.getType()))
                            .keyword(TableHint.Type.SNAPSHOT)
                            .and()
                        .czse(d -> TableHint.Type.SPATIAL_WINDOW_MAX_CELLS.equals(d.getType()))
                            .sub()
                                .keyword(TableHint.Type.SPATIAL_WINDOW_MAX_CELLS)
                                .and()
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub("integer")
                                .data(TableHint::getInteger)
                                .and()
                            .and()
                        .czse(d -> TableHint.Type.TABLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.TABLOCK)
                            .and()
                        .czse(d -> TableHint.Type.TABLOCKX.equals(d.getType()))
                            .keyword(TableHint.Type.TABLOCKX)
                            .and()
                        .czse(d -> TableHint.Type.UPDLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.UPDLOCK)
                            .and()
                        .czse(d -> TableHint.Type.XLOCK.equals(d.getType()))
                            .keyword(TableHint.Type.XLOCK)
                            .and()
                        .subTakeLine()
                        .headFootTakeLine()
                        .and()
                    .subTakeLine()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
