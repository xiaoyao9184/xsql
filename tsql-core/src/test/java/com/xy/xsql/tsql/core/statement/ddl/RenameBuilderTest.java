package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.statement.ddl.ReNameBuilder.rename_db;
import static com.xy.xsql.tsql.core.statement.ddl.ReNameBuilder.rename_t;


/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class RenameBuilderTest {


    /**
     * RENAME DATABASE AdWorks to AdWorks2;
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        ReName reName = rename_db("AdWorks","AdWorks2");
        // @formatter:on

        Assert.assertEquals(reName.getDbName(),"AdWorks");
        Assert.assertEquals(reName.getNewName(),"AdWorks2");
    }

    /**
     * RENAME OBJECT Customer TO Customer1;

     RENAME OBJECT mydb.dbo.Customer TO Customer1;
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        ReName reName = rename_t("Customer","Customer1");
        ReName reName2 = rename_t(t("mydb", "dbo","Customer"),"Customer1");
        // @formatter:on

        Assert.assertEquals(reName.getTableName().toString(),"Customer");
        Assert.assertEquals(reName.getNewName(),"Customer1");
        Assert.assertEquals(reName2.getTableName().toString(),"mydb.dbo.Customer");
        Assert.assertEquals(reName2.getNewName(),"Customer1");
    }

}
