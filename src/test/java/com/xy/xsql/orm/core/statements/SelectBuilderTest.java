package com.xy.xsql.orm.core.statements;

import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.statements.dml.Select;
import org.junit.Assert;
import org.junit.Test;

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
                    .withCountExpression(50)
                    .build(null)
                .build(null);

        Assert.assertEquals(select.getTop().getCountExpression().toString(),"50");

        select = new SelectBuilder()
                .withTop()
                    .withCountExpression(50)
                    .withPercent()
                    .build(null)
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

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),2);
    }
}
