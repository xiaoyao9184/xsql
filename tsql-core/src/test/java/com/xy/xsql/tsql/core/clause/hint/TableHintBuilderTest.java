package com.xy.xsql.tsql.core.clause.hint;

import com.xy.xsql.tsql.core.clause.hints.TableHintBuilder;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableHintBuilderTest {

    // @formatter:off
    /**
     * TABLOCK
     */
    public TableHint exampleA = TABLOCK();
    // @formatter:on

    @Test
    public void testExampleA() {
        TableHint queryHint = new TableHintBuilder<Void>()
                .withType(TableHint.Type.TABLOCK)
                .build();

        Assert.assertEquals(queryHint.getType(),TableHint.Type.TABLOCK);
    }


    // @formatter:off
    /**
     * FORCESEEK
     */
    public TableHint exampleB1 = FORCESEEK();

    /**
     * FORCESEEK (PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID (SalesOrderID))
     */
    public TableHint exampleB2 = FORCESEEK("PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID","SalesOrderID");
    // @formatter:on

    @Test
    public void testExampleB() {
        Assert.assertEquals(exampleB1.getType(),TableHint.Type.FORCESEEK);
        Assert.assertEquals(exampleB2.getType(),TableHint.Type.FORCESEEK);
        Assert.assertEquals(exampleB2.getIndex_value().get(0).toString(),"PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID");
        Assert.assertEquals(exampleB2.getIndex_column_name().get(0).toString(),"SalesOrderID");
    }


    // @formatter:off
    /**
     * FORCESCAN
     */
    public TableHint exampleC = FORCESCAN();
    // @formatter:on

    @Test
    public void testExampleC() {
        Assert.assertEquals(exampleC.getType(),TableHint.Type.FORCESCAN);
    }

}
