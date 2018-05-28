package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.queries.select.Into;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.into.INTO;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
public class intoTest {

    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-into-clause-transact-sql#examples
     */

    // @formatter:off
    //parent+quick

    /**
     * INTO dbo.EmployeeAddresses
     */
    public Into exampleA = INTO(
            t("dbo","EmployeeAddresses")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getNewTable().toString(), "dbo.EmployeeAddresses");
    }


    // @formatter:off
    //parent+quick
    /**
     * INTO [dbo].[FactResellerSalesXL] ON FG2
     */
    public Into exampleF = INTO(
            t("[dbo]","[FactResellerSalesXL]")
    ).ON("FG2").build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getNewTable().toString(), "[dbo].[FactResellerSalesXL]");
    }

}