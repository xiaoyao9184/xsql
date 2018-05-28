package com.xy.xsql.tsql.style.constructor.introducer.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.TableHint;
import org.junit.Test;

import static com.xy.xsql.tsql.style.constructor.introducer.queries.hints.table_hint.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/11.
 */
public class table_hintTest {

    // @formatter:off
    /**
     * TABLOCK
     */
    public TableHint exampleA = TABLOCK().build();
    // @formatter:on

    @Test
    public void testExampleA() {
        assertEquals(exampleA.getType(),TableHint.Type.TABLOCK);
    }


    // @formatter:off
    /**
     * FORCESEEK
     */
    public TableHint exampleB1 = FORCESEEK().build();

    /**
     * FORCESEEK (PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID (SalesOrderID))
     */
    public TableHint exampleB2 = FORCESEEK("PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID","SalesOrderID").build();
    // @formatter:on

    @Test
    public void testExampleB() {
        assertEquals(exampleB1.getType(),TableHint.Type.FORCESEEK);
        assertEquals(exampleB2.getType(),TableHint.Type.FORCESEEK);
        assertEquals(exampleB2.getIndex_value().get(0).toString(),"PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID");
        assertEquals(exampleB2.getIndex_column_name().get(0).toString(),"SalesOrderID");
    }


    // @formatter:off
    /**
     * FORCESCAN
     */
    public TableHint exampleC = FORCESCAN().build();
    // @formatter:on

    @Test
    public void testExampleC() {
        assertEquals(exampleC.getType(),TableHint.Type.FORCESCAN);
    }

}