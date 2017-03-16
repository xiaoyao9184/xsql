package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.e_rv;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilderTest {

    /**
     * SELECT * FROM table
     */
    @Test
    public void testBaseBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectList()
                    .withSelectItem()
                        .withAll()
                        .build(null)
                    .build(null)
                .withFrom()
                    .withTableSource()
                        .withTableName("table")
                        .and()
                    .and()
                .build(null);

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
        Assert.assertEquals(select.getSelectList().getList().size(),1);
    }

    /**
     * SELECT ALL * FROM table
     */
    @Test
    public void testAllBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withAll()
                .build(null);

        Assert.assertTrue(select.isUseAll());
    }

    /**
     * SELECT DISTINCT * FROM table
     */
    @Test
    public void testDistinctBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withDistinct()
                .build(null);

        Assert.assertTrue(select.isUseDistinct());
    }

    /**
     * SELECT TOP 50 * FROM table
     * SELECT TOP 50 PERCENT * FROM table
     */
    @Test
    public void testTopBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withTop()
                    .withExpression(e(50))
                    .and()
                .build(null);

        Assert.assertEquals(select.getTop().getExpression().toString(),"50");

        select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withTop()
                    .withExpression(e(50))
                    .withPercent()
                    .and()
                .build(null);

        Assert.assertTrue(select.getTop().isUsePercent());
    }

    /**
     * SELECT * FROM table,table2
     */
    @Test
    public void test2TableBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectList()
                    .withSelectItem()
                        .withAll()
                        .build(null)
                    .build(null)
                .withFrom()
                    .withTableSource()
                        .withTableName("table")
                        .and()
                    .withTableSource()
                        .withTableName("table2")
                        .and()
                    .and()
                .build(null);

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),2);
    }

    /**
     * SELECT * FROM table LEFT JOIN table2
     */
    @Test
    public void testTableJoinBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectList()
                    .withSelectItem()
                        .withAll()
                        .build(null)
                    .build(null)
                .withFrom()
                    .withTableSource()
                        .withJoinedTable()
                            .withTableSource()
                                .withTableName("table")
                                .and()
                            .withJoinType(From.JoinType.LEFT_JOIN)
                            .withTableSource2()
                                .withTableName("table2")
                                .and()
                            .and()
                        .and()
                    .and()
                .build(null);

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
    }


    /**
     * SELECT * FROM (VALUES (1, 2), (3, 4), (5, 6), (7, 8), (9, 10) ) AS MyTable(a, b)
     */
    @Test
    public void testDerivedTable(){
        // @formatter:off
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectList()
                    .withSelectItem().withAll()
                    .out()
                .out()
                .withFrom()
                    .withTableSource()
                        .withDerivedTable().values()
                            .withRowValues()
                                .withRowValueExpression(e_rv(1))
                                .withRowValueExpression(e_rv(2))
                                .out()
                            .withRowValues()
                                .withRowValueExpression(e_rv(3))
                                .withRowValueExpression(e_rv(4))
                                .out()
                            .withRowValues()
                                .withRowValueExpression(e_rv(5))
                                .withRowValueExpression(e_rv(6))
                                .out()
                            .withRowValues()
                                .withRowValueExpression(e_rv(7))
                                .withRowValueExpression(e_rv(8))
                                .out()
                            .withRowValues()
                                .withRowValueExpression(e_rv(9))
                                .withRowValueExpression(e_rv(10))
                                .out()
                            .out()
                        .out()
                        //TODO derived_table [ [ AS ] table_alias ] [ ( column_alias [ ,...n ] ) ]
                    .out()
                .out()
                .build(null);
        // @formatter:on
    }


}
