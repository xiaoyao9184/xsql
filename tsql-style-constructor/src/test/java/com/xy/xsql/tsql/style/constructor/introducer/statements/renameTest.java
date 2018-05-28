package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.statements.ReName;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.rename.RENAME_DATABASE;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.rename.RENAME_OBJECT;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/5/28.
 */
public class renameTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/rename-transact-sql#examples
     */

    // @formatter:off
    /**
     * -- Rename the user defined database AdWorks
    RENAME DATABASE AdWorks to AdWorks2
     */
    public ReName exampleA = RENAME_DATABASE("AdWorks","AdWorks2").build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getDbName(),"AdWorks");
        assertEquals(exampleA.getNewName(),"AdWorks2");
    }


    // @formatter:off
    /**
     * -- Rename the customer table
    RENAME OBJECT Customer TO Customer1
     */
    public ReName exampleB1 = RENAME_OBJECT(t("Customer"),"Customer1").build();

    /**
     * RENAME OBJECT mydb.dbo.Customer TO Customer1
     */
    public ReName exampleB2 = RENAME_OBJECT(t("mydb", "dbo","Customer"),"Customer1").build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB1.getTableName().toString(),"Customer");
        assertEquals(exampleB1.getNewName(),"Customer1");
        assertEquals(exampleB2.getTableName().toString(),"mydb.dbo.Customer");
        assertEquals(exampleB2.getNewName(),"Customer1");
    }

}