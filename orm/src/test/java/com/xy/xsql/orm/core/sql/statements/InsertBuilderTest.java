package com.xy.xsql.orm.core.sql.statements;

import com.xy.xsql.orm.data.sql.expression.NumberString;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class InsertBuilderTest {

    /**
     * INSERT table (c1, c2) VALUES (1, 2)
     */
    @Test
    public void testBaseBuild(){
        Insert insert = new InsertBuilder()
                .withTableName("table")
                .withColumnList()
                    .withItem()
                        .withName("c1")
                        .out()
                    .withItem()
                        .withName("c2")
                        .out()
                    .out()
                .withValues()
                    .withGroupItem()
                        .withItem()
                            .withExpression(new NumberString(1))
                            .out()
                        .withItem()
                            .withExpression(new NumberString(2))
                            .out()
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(insert.getTableName().toString(),"table");
        Assert.assertEquals(insert.getColumns().size(),2);
    }

    /**
     * INSERT INTO table (c1, c2) VALUES (1, 2)
     */
    @Test
    public void testIntoBuild(){
        Insert insert = new InsertBuilder()
                .withInto(true)
                .withTableName("table")
                .withColumnList()
                    .withItem()
                        .withName("c1")
                        .out()
                    .withItem()
                        .withName("c2")
                        .out()
                    .out()
                .withValues()
                .withGroupItem()
                        .withItem()
                            .withExpression(new NumberString(1))
                            .out()
                        .withItem()
                            .withExpression(new NumberString(2))
                            .out()
                        .out()
                    .out()
                .build(null);

        Assert.assertTrue(insert.isUseInto());
    }

}
