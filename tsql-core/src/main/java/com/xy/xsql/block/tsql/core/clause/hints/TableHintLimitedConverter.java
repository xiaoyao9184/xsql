package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableHintLimitedConverter
        implements MetaContextBlockConverter<TableHintLimited> {

    // @formatter:off
    private static BlockMetaBuilder<Void,TableHintLimited> builder =
            new BlockMetaBuilder<Void,TableHintLimited>()
                    .overall("table_hint_limited")
                    .czse(d -> d.equals(TableHintLimited.KEEPIDENTITY))
                        .keyword(TableHintLimited.KEEPIDENTITY)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.KEEPDEFAULTS))
                        .keyword(TableHintLimited.KEEPDEFAULTS)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.HOLDLOCK))
                        .keyword(TableHintLimited.HOLDLOCK)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.IGNORE_CONSTRAINTS))
                        .keyword(TableHintLimited.IGNORE_CONSTRAINTS)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.IGNORE_TRIGGERS))
                        .keyword(TableHintLimited.IGNORE_TRIGGERS)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.NOLOCK))
                        .keyword(TableHintLimited.NOLOCK)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.NOWAIT))
                        .keyword(TableHintLimited.NOWAIT)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.PAGLOCK))
                        .keyword(TableHintLimited.PAGLOCK)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.READPAST))
                        .keyword(TableHintLimited.READPAST)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.READCOMMITTED))
                        .keyword(TableHintLimited.READCOMMITTED)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.READCOMMITTEDLOCK))
                        .keyword(TableHintLimited.READCOMMITTEDLOCK)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.READPAST))
                        .keyword(TableHintLimited.READPAST)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.REPEATABLEREAD))
                        .keyword(TableHintLimited.REPEATABLEREAD)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.ROWLOCK))
                        .keyword(TableHintLimited.ROWLOCK)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.SERIALIZABLE))
                        .keyword(TableHintLimited.SERIALIZABLE)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.SNAPSHOT))
                        .keyword(TableHintLimited.SNAPSHOT)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.TABLOCK))
                        .keyword(TableHintLimited.TABLOCK)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.TABLOCKX))
                        .keyword(TableHintLimited.TABLOCKX)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.UPDLOCK))
                        .keyword(TableHintLimited.UPDLOCK)
                        .and()
                    .czse(d -> d.equals(TableHintLimited.XLOCK))
                        .keyword(TableHintLimited.XLOCK)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(TableHintLimited context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
