package com.xy.xsql.block.tsql.core.element.column;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.element.constraint.CheckConverters;
import com.xy.xsql.block.tsql.core.element.constraint.ForeignConverters;
import com.xy.xsql.block.tsql.core.element.constraint.PrimaryUniqueConverters;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class ColumnConstraintConverters {

    public static class DiskBased {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnConstraint>()
                        .overall("column_constraint")
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
                                .ref(PrimaryUniqueConverters.DiskBasedColumn.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Foreign)
                                .ref(ForeignConverters.DiskBasedColumn.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Check)
                                .ref(CheckConverters.Replication.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .syntax_required()
                            .syntax_context_indentation()
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

    }

    public static class MemoryOptimized {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnConstraint>()
                        .overall("column_constraint")
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
                                .ref(PrimaryUniqueConverters.MemoryOptimizedColumn.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Foreign)
                                .ref(ForeignConverters.MemoryOptimizedColumn.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .czse(d -> d.getConstraint() instanceof Check)
                                .ref(CheckConverters.Simple.meta)
                                .scope(d -> d.getConstraint())
                                .and()
                            .syntax_required()
                            .syntax_context_indentation()
                            .syntax_sub_line()
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on
    }
}
