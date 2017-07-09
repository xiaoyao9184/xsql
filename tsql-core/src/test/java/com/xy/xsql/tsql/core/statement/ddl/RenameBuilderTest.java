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

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/rename-transact-sql#examples
     */

    // @formatter:off
    /**
     * -- Rename the user defined database AdWorks
    RENAME DATABASE AdWorks to AdWorks2
     */
    public ReName exampleA = RENAME_DATABASE("AdWorks","AdWorks2");
    // @formatter:on

    @Test
    public void testExampleA(){
        ReName reName = new ReNameBuilder()
                .withDBName("AdWorks")
                .withNewName("AdWorks2")
                .build();

        Assert.assertEquals(reName.getDbName(),"AdWorks");
        Assert.assertEquals(reName.getNewName(),"AdWorks2");
    }


    // @formatter:off
    /**
     * -- Rename the customer table
    RENAME OBJECT Customer TO Customer1
     */
    public ReName exampleB1 = RENAME_TABLE("Customer","Customer1");

    /**
     * RENAME OBJECT mydb.dbo.Customer TO Customer1
     */
    public ReName exampleB2 = RENAME_TABLE("mydb", "dbo","Customer","Customer1");
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        ReName reName1 = new ReNameBuilder()
                .withTableName(t("Customer"))
                .withNewName("Customer1")
                .build();

        ReName reName2 = new ReNameBuilder()
                .withTableName(t("mydb", "dbo","Customer"))
                .withNewName("Customer1")
                .build();
        // @formatter:on

        Assert.assertEquals(reName1.getTableName().toString(),"Customer");
        Assert.assertEquals(reName1.getNewName(),"Customer1");
        Assert.assertEquals(reName2.getTableName().toString(),"mydb.dbo.Customer");
        Assert.assertEquals(reName2.getNewName(),"Customer1");
    }

}
