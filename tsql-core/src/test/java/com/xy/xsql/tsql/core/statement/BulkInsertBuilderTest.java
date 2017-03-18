package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.statement.BulkInsertBuilder._ACP;
import static com.xy.xsql.tsql.core.statement.BulkInsertBuilder._char;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class BulkInsertBuilderTest {

    /**
     * BULK INSERT table FROM 'D:/backup.cvs' WITH ( FORMAT = 'CSV' )
     */
    @Test
    public void testBaseBuild(){
        BulkInsert insert = new BulkInsertBuilder()
                .withTableViewName(new TableName("table"))
                .withFrom("D:/backup.cvs")
                .withFormat("CVS")
                .build(null);

        Assert.assertEquals(insert.getFormat().toString(),"CVS");
    }

    @Test
    public void testCodePageDataFileType(){
        BulkInsert insert = new BulkInsertBuilder()
            .withTableViewName(new TableName("table"))
            .withFrom("D:/backup.cvs")
            .withFormat("CVS")
            .withCodePage(_ACP())
            .withDataFileType(_char())
            .build(null);

        Assert.assertEquals(insert.getCodePage().toString(),"ACP");
        Assert.assertEquals(insert.getDataFileType().toString(),"Char");
    }



}
