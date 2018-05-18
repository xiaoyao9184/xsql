package com.xy.xsql.block.tsql.core.element.table;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.constraint.CheckConverters;
import com.xy.xsql.block.tsql.core.element.constraint.ForeignConverters;
import com.xy.xsql.block.tsql.core.element.constraint.PrimaryUniqueConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.element.constraint.Check;
import com.xy.xsql.tsql.model.element.constraint.Foreign;
import com.xy.xsql.tsql.model.element.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.element.table.TableConstraint;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class TableConstraintConverters {

    public static class DiskBased {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableConstraint>()
                        .overall("table_constraint")
                        .sub()
                            .description("constraint name")
                            .optional(d -> d.getConstraintName() == null)
                            .sub_keyword(Keywords.CONSTRAINT)
                            .sub("constraint_name")
                                .scope(d -> d.getConstraintName())
                                .and()
                            .and()
                        .sub()
                            .description("constraint")
                            .czse(d -> d.getConstraint() instanceof PrimaryUnique)
                                .ref(PrimaryUniqueConverters.DiskBasedTable.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Foreign)
                                .ref(ForeignConverters.DiskBasedTable.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Check)
                                .ref(CheckConverters.Replication.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .syntax_required()
                            .syntax_line()
                            .syntax_context_indentation()
                            .and()
                        .build();
        // @formatter:on

    }

    public static class MemoryOptimized {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableConstraint>()
                        .overall("table_constraint")
                        .sub()
                            .description("constraint name")
                            .optional(d -> d.getConstraintName() == null)
                            .sub_keyword(Keywords.CONSTRAINT)
                            .sub("constraint_name")
                                .scope(d -> d.getConstraintName())
                                .and()
                            .and()
                        .sub()
                            .description("constraint")
                            .czse(d -> d.getConstraint() instanceof PrimaryUnique)
                                .ref(PrimaryUniqueConverters.MemoryOptimizedTable.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Foreign)
                                .ref(ForeignConverters.MemoryOptimizedTable.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Check)
                                .ref(CheckConverters.Simple.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .syntax_required()
                            .syntax_line()
                            .syntax_context_indentation()
                            .syntax_sub_line()
                            .and()
                        .build();
        // @formatter:on
    }

}
