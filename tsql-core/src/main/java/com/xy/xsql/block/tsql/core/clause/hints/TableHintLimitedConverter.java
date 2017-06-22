package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableHintLimitedConverter
        implements BlockConverter<TableHintLimited> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,TableHintLimited> builder =
            new ReferenceBlockBuilder<Void,TableHintLimited>()
                    .overall("table_hint_limited")
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.KEEPIDENTITY))
                        .keyword(TableHintLimited.KEEPIDENTITY)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.KEEPDEFAULTS))
                        .keyword(TableHintLimited.KEEPDEFAULTS)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.HOLDLOCK))
                        .keyword(TableHintLimited.HOLDLOCK)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.IGNORE_CONSTRAINTS))
                        .keyword(TableHintLimited.IGNORE_CONSTRAINTS)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.IGNORE_TRIGGERS))
                        .keyword(TableHintLimited.IGNORE_TRIGGERS)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.NOLOCK))
                        .keyword(TableHintLimited.NOLOCK)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.NOWAIT))
                        .keyword(TableHintLimited.NOWAIT)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.PAGLOCK))
                        .keyword(TableHintLimited.PAGLOCK)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.READPAST))
                        .keyword(TableHintLimited.READPAST)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.READCOMMITTED))
                        .keyword(TableHintLimited.READCOMMITTED)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.READCOMMITTEDLOCK))
                        .keyword(TableHintLimited.READCOMMITTEDLOCK)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.READPAST))
                        .keyword(TableHintLimited.READPAST)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.REPEATABLEREAD))
                        .keyword(TableHintLimited.REPEATABLEREAD)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.ROWLOCK))
                        .keyword(TableHintLimited.ROWLOCK)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.SERIALIZABLE))
                        .keyword(TableHintLimited.SERIALIZABLE)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.SNAPSHOT))
                        .keyword(TableHintLimited.SNAPSHOT)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.TABLOCK))
                        .keyword(TableHintLimited.TABLOCK)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.TABLOCKX))
                        .keyword(TableHintLimited.TABLOCKX)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.UPDLOCK))
                        .keyword(TableHintLimited.UPDLOCK)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(TableHintLimited.XLOCK))
                        .keyword(TableHintLimited.XLOCK)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(TableHintLimited tableHintLimited) {
        return builder
                .data(tableHintLimited)
                .build();
    }

}
