package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.TreeBlock;
import com.xy.xsql.tsql.core.SimpleBlockRenderer;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.clause.Option;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.clause.select.For;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class SelectConverter
        implements BlockConverter<Select> {

    @Deprecated
    public List<Block> getChildBlock(Select select){
        ListBlockBuilder b = new ListBlockBuilder()
                .withAutoRemoveNull(false);

        b.append(select.getWith());
        b.append(select.getQueryExpression());
        b.append(select.getOrderBy());
        b.append(select.getForClause());
        b.append(select.getOption());
        return b.build();
    }

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Select> builder =
            new ReferenceBlockBuilder<Void,Select>()
                    .overall("SELECT statement")
                    .sub("[ WITH { [ XMLNAMESPACES ,] [ <common_table_expression> [,...n] ] } ]")
                        .optional(d -> d.getWith() == null)
                        .data(Select::getWith)
                        .and()
                    .sub("query_specification")
                        .ref(Select.QuerySpecification.class)
                        .data(Select::getQueryExpression)
                        .and()
                    .sub("ORDER BY { order_by_expression | column_position [ ASC | DESC ] } [ ,...n ]")
                        .optional(d -> d.getOrderBy() == null)
                        .data(Select::getOrderBy)
                        .and()
                    .sub("FOR Clause")
                        .optional(d -> d.getForClause() == null)
                        .ref(For.class)
                        .data(Select::getForClause)
                        .and()
                    .sub("OPTION ( <query_hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .data(Select::getOption)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Select select) {
        return builder
                .data(select)
                .build();
    }



}
