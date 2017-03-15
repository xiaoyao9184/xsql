package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.model.statement.dml.Delete;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class DeleteBuilderTest {

    /**
     * DELETE table
     */
    @Test
    public void testBaseBuild(){
        Delete delete = new DeleteBuilder()
                .withTableAlias("table")
                .build(null);

        Assert.assertEquals(delete.getTableAlias().toString(),"table");
    }

    /**
     * UPDATE t FROM table
     */
    @Test
    public void testFromBuild(){
        Delete delete = new DeleteBuilder()
                .withTableAlias("t")
                .withFrom()
                    .withTableSource()
                        .withTable("table")
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(delete.getFrom().getTableSourceList().size(),1);
    }

}
