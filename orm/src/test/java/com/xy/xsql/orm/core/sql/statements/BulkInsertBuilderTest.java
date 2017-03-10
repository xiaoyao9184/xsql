package com.xy.xsql.orm.core.sql.statements;

import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.statements.dml.BulkInsert;
import org.junit.Assert;
import org.junit.Test;

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


}
