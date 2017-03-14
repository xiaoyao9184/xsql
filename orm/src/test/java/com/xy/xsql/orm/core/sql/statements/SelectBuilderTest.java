package com.xy.xsql.orm.core.sql.statements;

import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.orm.data.sql.statements.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilderTest {

    /**
     * SELECT * FROM table
     */
    @Test
    public void testBaseBuild(){
        Select select = new SelectBuilder()
                .withSelectList()
                    .withSelectItem()
                        .withAll()
                        .build(null)
                    .build(null)
                .withFrom()
                    .withTableSource()
                        .withTable("table")
                        .build(null)
                    .build(null)
                .build(null);

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
        Assert.assertEquals(select.getSelectList().getList().size(),1);
    }

    /**
     * SELECT ALL * FROM table
     */
    @Test
    public void testAllBuild(){
        Select select = new SelectBuilder()
                .withAll()
                .build(null);

        Assert.assertTrue(select.isUseAll());
    }

    /**
     * SELECT DISTINCT * FROM table
     */
    @Test
    public void testDistinctBuild(){
        Select select = new SelectBuilder()
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
        Select select = new SelectBuilder()
                .withTop()
                    .withExpression(e(50))
                    .and()
                .build(null);

        Assert.assertEquals(select.getTop().getExpression().toString(),"50");

        select = new SelectBuilder()
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
        Select select = new SelectBuilder()
                .withSelectList()
                    .withSelectItem()
                        .withAll()
                        .build(null)
                    .build(null)
                .withFrom()
                    .withTableSource()
                        .withTable("table")
                        .build(null)
                    .withTableSource()
                        .withTable("table2")
                        .build(null)
                    .build(null)
                .build(null);

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),2);
    }

    /**
     * SELECT * FROM table LEFT JOIN table2
     */
    @Test
    public void testTableJoinBuild(){
        Select select = new SelectBuilder()
                .withSelectList()
                    .withSelectItem()
                        .withAll()
                        .build(null)
                    .build(null)
                .withFrom()
                    .withTableSource()
                        .withJoinedTable()
                            .withTableSource()
                                .withTable("table")
                                .build(null)
                            .withJoinType(From.JoinType.LEFT_JOIN)
                            .withTableSource2()
                                .withTable("table2")
                                .build(null)
                            .build(null)
                        .build(null)
                    .build(null)
                .build(null);

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
    }


    /**
     * SELECT * FROM (VALUES (1, 2), (3, 4), (5, 6), (7, 8), (9, 10) ) AS MyTable(a, b)
     */
    @Test
    public void testDerivedTable(){
        // @formatter:off
        Select select = new SelectBuilder()
                .withSelectList()
                    .withSelectItem().withAll()
                    .out()
                .out()
                .withFrom()
                    .withTableSource()
                        .withDerivedTable().values()
                            .withGroupItem()
                                .withItem().withExpression(e(1)).out()
                                .withItem().withExpression(e(2)).out()
                            .out()
                            .withGroupItem()
                                .withItem().withExpression(e(3)).out()
                                .withItem().withExpression(e(4)).out()
                            .out()
                            .withGroupItem()
                                .withItem().withExpression(e(5)).out()
                                .withItem().withExpression(e(6)).out()
                            .out()
                            .withGroupItem()
                                .withItem().withExpression(e(7)).out()
                                .withItem().withExpression(e(8)).out()
                            .out()
                            .withGroupItem()
                                .withItem().withExpression(e(9)).out()
                                .withItem().withExpression(e(10)).out()
                            .out()
                        .out().out()
                        //TODO derived_table [ [ AS ] table_alias ] [ ( column_alias [ ,...n ] ) ]
                    .out()
                .out()
                .build(null);
        // @formatter:on
    }
}
