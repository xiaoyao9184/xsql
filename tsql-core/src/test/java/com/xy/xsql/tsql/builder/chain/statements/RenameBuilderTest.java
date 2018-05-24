package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.tsql.model.statements.ReName;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$RenameDatabase;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$RenameTable;
import static org.junit.Assert.*;

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
    public ReName exampleA = $RenameDatabase("AdWorks","AdWorks2");
    // @formatter:on

    @Test
    public void testExampleA(){
        ReName reName = new ReNameBuilder()
                .withDBName("AdWorks")
                .withNewName("AdWorks2")
                .build();

        assertEquals(reName.getDbName(),"AdWorks");
        assertEquals(reName.getNewName(),"AdWorks2");
    }


    // @formatter:off
    /**
     * -- Rename the customer table
    RENAME OBJECT Customer TO Customer1
     */
    public ReName exampleB1 = Statements.$RenameTable("Customer","Customer1");

    /**
     * RENAME OBJECT mydb.dbo.Customer TO Customer1
     */
    public ReName exampleB2 = Statements.$RenameTable("mydb", "dbo","Customer","Customer1");
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

        assertEquals(reName1.getTableName().toString(),"Customer");
        assertEquals(reName1.getNewName(),"Customer1");
        assertEquals(reName2.getTableName().toString(),"mydb.dbo.Customer");
        assertEquals(reName2.getNewName(),"Customer1");
    }

}
