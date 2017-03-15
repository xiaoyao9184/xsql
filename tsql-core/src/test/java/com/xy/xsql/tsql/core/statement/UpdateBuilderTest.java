package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.model.statement.dml.Update;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class UpdateBuilderTest {

    /**
     * UPDATE table SET c1 = 1, c2 = 2
     */
    @Test
    public void testBaseBuild(){
        Update update = new UpdateBuilder()
                .withTableName("table")
                .withSetList()
                    .withItem()
                        .withColumnName("c1")
                        .withExpression(e_number(1))
                        .out()
                    .withItem()
                        .withColumnName("c2")
                        .withExpression(e_number(2))
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(update.getTableName().getTableOrViewName(),"table");
        Assert.assertEquals(update.getSets().size(),2);
    }

    /**
     * UPDATE table SET c1 = c3, c2 = c4 FROM table2
     */
    @Test
    public void testFromBuild(){
        Update update = new UpdateBuilder()
                .withTableName("table")
                .withSetItem()
                    .withColumnName("c1")
                    .withExpression(c("c3"))
                    .out()
                .withSetItem()
                    .withColumnName("c2")
                    .withExpression(c("c4"))
                    .out()
                .withFrom()
                    .withTableSource()
                        .withTable("table2")
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(update.getFrom().getTableSourceList().size(),1);
    }

}
