package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.index.PartitionConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.other.OnOff;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statements.alter.table.DropClusteredConstraintOption;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class DropClusteredConstraintOptionConverter
        implements ModelMetaBlockConverter<DropClusteredConstraintOption> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DropClusteredConstraintOption>()
                    .overall("drop_clustered_constraint_option")
                    .czse(d -> d.getMaxDegreeOfParallelism() != null)
                        .sub_keyword(Keywords.Key.MAXDOP)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("max_degree_of_parallelism")
                            .scope(d -> d.getMaxDegreeOfParallelism().withUnsigned().withInteger())
                            .and()
                        .and()
                    .czse(d -> d.getOnline() != null)
                        .sub_keyword(Keywords.Key.ONLINE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> d.getOnline().equals(OnOff.ON), Keywords.ON)
                            .czse_keyword(d -> d.getOnline().equals(OnOff.OFF), Keywords.OFF)
                            .syntax_required()
                            .and()
                        .and()
                    .czse(d -> d.getMoveTo() != null)
                        .sub_keyword(Keywords.Key.MOVE)
                        .sub_keyword(Keywords.TO)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .sub()
                                .ref(PartitionConverters.PartitionColumnFilegroupDefaultKey.meta)
                                .scope(d -> d.getMoveTo())
                                .and()
                            .syntax_line()
                            .syntax_indentation_right()
                            .and()
                        .and()
                    .syntax_required()
                    .syntax_context_indentation()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
