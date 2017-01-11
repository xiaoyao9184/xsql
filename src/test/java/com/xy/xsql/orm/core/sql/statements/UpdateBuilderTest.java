package com.xy.xsql.orm.core.sql.statements;

import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.expression.NumberString;
import com.xy.xsql.orm.data.sql.statements.dml.Update;
import org.junit.Assert;
import org.junit.Test;

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
                        .withExpression(new NumberString(1))
                        .out()
                    .withItem()
                        .withColumnName("c2")
                        .withExpression(new NumberString(2))
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(update.getTableName().getName(),"table");
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
                    .withExpression(new Column("c3"))
                    .out()
                .withSetItem()
                    .withColumnName("c2")
                    .withExpression(new Column("c4"))
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
