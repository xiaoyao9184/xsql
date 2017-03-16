package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.e_rv;

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
                .withColumn(c("c1"))
                .withColumn(c("c2"))
                .withValues()
                    .withRowValues()
                        .withRowValueExpression(e_rv(1))
                        .withRowValueExpression(e_rv(2))
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
                .withColumn(c("c1"))
                .withColumn(c("c2"))
                .withValues()
                    .withRowValues()
                        .withRowValueExpression(e_rv(1))
                        .withRowValueExpression(e_rv(2))
                        .out()
                    .out()
                .build(null);

        Assert.assertTrue(insert.isUseInto());
    }

}
