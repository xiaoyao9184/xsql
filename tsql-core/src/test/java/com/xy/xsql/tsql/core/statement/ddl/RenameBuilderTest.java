package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.statement.ddl.ReNameBuilder.RENAME_DATABASE;
import static com.xy.xsql.tsql.core.statement.ddl.ReNameBuilder.RENAME_TABLE;


/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class RenameBuilderTest {

    // @formatter:off
    public ReName exampleA = RENAME_DATABASE("AdWorks","AdWorks2");
    // @formatter:on

    /**
     * RENAME DATABASE AdWorks to AdWorks2;
     */
    @Test
    public void testExampleA(){
        Assert.assertEquals(exampleA.getDbName(),"AdWorks");
        Assert.assertEquals(exampleA.getNewName(),"AdWorks2");
    }


    // @formatter:off
    public ReName exampleB1 = RENAME_TABLE("Customer","Customer1");
    public ReName exampleB2 = RENAME_TABLE("mydb", "dbo","Customer","Customer1");
    // @formatter:on

    /**
     * RENAME OBJECT Customer TO Customer1;

     RENAME OBJECT mydb.dbo.Customer TO Customer1;
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        ReName reName2 = RENAME_TABLE(t("mydb", "dbo","Customer"),"Customer1");
        // @formatter:on

        Assert.assertEquals(exampleB1.getTableName().toString(),"Customer");
        Assert.assertEquals(exampleB1.getNewName(),"Customer1");
        Assert.assertEquals(reName2.getTableName().toString(),"mydb.dbo.Customer");
        Assert.assertEquals(reName2.getNewName(),"Customer1");
        Assert.assertEquals(exampleB2.getTableName().toString(),"mydb.dbo.Customer");
        Assert.assertEquals(exampleB2.getNewName(),"Customer1");
    }

}
