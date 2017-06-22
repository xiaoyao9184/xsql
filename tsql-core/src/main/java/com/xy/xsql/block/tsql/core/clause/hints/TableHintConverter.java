package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.operator.Operators;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableHintConverter
        implements BlockConverter<TableHint> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,TableHint> builder =
            new ReferenceBlockBuilder<Void,TableHint>()
                    .overall("table_hint")
                    .sub()
                        .optional(TableHint::isUseNOEXPAND)
                        .keyword(Keywords.Key.NOEXPAND)
                        .and()
                    .sub()
                        .required()
                        .oneOf()
                            .description("INDEX ( index_value [ ,...n ] )")
                            .sub_keyword(Keywords.INDEX)
                            .sub_keyword(Other.GROUP_START)
                            .sub("index_value")
                                .list()
                                .data(TableHint::getIndex_value)
                                .more()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .oneOf()
                            .sub_keyword(Keywords.INDEX)
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub_keyword(Other.GROUP_START)
                            .sub("index_value")
                                .data(d -> d.getIndex_value().get(0))
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .oneOf()
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
                                .sub("index_column_name")
                                    .list()
                                    .data(TableHint::getIndex_column_name)
                                    .more()
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.FORCESCAN)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.FORCESEEK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.HOLDLOCK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.NOLOCK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.NOWAIT)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.PAGLOCK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.READCOMMITTED)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.READCOMMITTEDLOCK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.READPAST)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.READUNCOMMITTED)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.REPEATABLEREAD)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.ROWLOCK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.SERIALIZABLE)
                            .and()
                        .oneOf("")
                            .keyword(TableHint.Type.SNAPSHOT)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.SNAPSHOT)
                            .and()
                        .oneOf()
                            .sub()
                                .keyword(TableHint.Type.SPATIAL_WINDOW_MAX_CELLS)
                                .and()
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub("integer")
                                .data(TableHint::getInteger)
                                .and()
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.TABLOCK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.TABLOCKX)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.UPDLOCK)
                            .and()
                        .oneOf()
                            .keyword(TableHint.Type.XLOCK)
                            .and()
                        .subTakeLine()
                        .headFootTakeLine()
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(TableHint tableHint) {
        return builder
                .data(tableHint)
                .build();
    }

}
