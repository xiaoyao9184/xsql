package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.tsql.builder.chain.MockParent;
import com.xy.xsql.tsql.builder.chain.MockParentBuilder;
import com.xy.xsql.tsql.model.queries.select.Into;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/28.
 */
public class IntoBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-into-clause-transact-sql#examples
     */

    // @formatter:off
    //parent+quick

    /**
     * INTO dbo.EmployeeAddresses
     */
    public Into exampleA = new MockParentBuilder<IntoBuilder<MockParent<Into>>,Into>
                (IntoBuilder.class,Into.class)
                .$child()
                    .withNewTable(t("dbo","EmployeeAddresses"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        Into into = new IntoBuilder<Void>()
                .withNewTable(t("dbo","EmployeeAddresses"))
                .build();
        // @formatter:on

        assertEquals(into.getNewTable().toString(), "dbo.EmployeeAddresses");
    }


    // @formatter:off
    //parent+quick
    /**
     * INTO [dbo].[FactResellerSalesXL] ON FG2
     */
    public Into exampleF = new MockParentBuilder<IntoBuilder<MockParent<Into>>,Into>
                (IntoBuilder.class,Into.class)
                .$child()
                    .withNewTable(t("[dbo]","[FactResellerSalesXL]"))
                    .withFileGroup("FG2")
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:off
        Into into = new IntoBuilder<Void>()
                .withNewTable(t("[dbo]","[FactResellerSalesXL]"))
                .withFileGroup("FG2")
                .build();
        // @formatter:on

        assertEquals(into.getNewTable().toString(), "[dbo].[FactResellerSalesXL]");
    }

}
