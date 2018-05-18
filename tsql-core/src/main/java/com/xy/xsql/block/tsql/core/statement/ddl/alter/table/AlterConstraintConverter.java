package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterConstraint;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class AlterConstraintConverter
        implements ModelMetaBlockConverter<AlterConstraint> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterConstraint>()
                    .description("alter table alter constraint item")
                    .sub()
                        .optional(d -> d.isUseWith())
                        .keyword(Keywords.WITH)
                        .and()
                    .sub()
                        .czse_keyword(d -> d.isUseCheck(),Keywords.CHECK)
                        .czse_keyword(d -> !d.isUseCheck(),Keywords.NOCHECK)
                        .syntax_required()
                        .and()
                    .sub_keyword(Keywords.CONSTRAINT)
                    .sub()
                        .description("constraint name list")
                        .czse_keyword(d -> d.getConstraintNames() == null,Keywords.ALL)
                        .czse(d -> d.getConstraintNames() != null,"constraint_name")
                            .list()
                            .scope(d -> d.getConstraintNames())
                            .and()
                        .syntax_line()
                        .syntax_indentation_right()
                        .syntax_required()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
