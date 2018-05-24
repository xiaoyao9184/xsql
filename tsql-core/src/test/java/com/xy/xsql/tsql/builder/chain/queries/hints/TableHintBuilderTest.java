package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.TableHint;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintBuilder.$Forcescan;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintBuilder.$Forceseek;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintBuilder.$Tablock;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableHintBuilderTest {

    // @formatter:off
    /**
     * TABLOCK
     */
    public TableHint exampleA = $Tablock();
    // @formatter:on

    @Test
    public void testExampleA() {
        TableHint queryHint = new TableHintBuilder<Void>()
                .withType(TableHint.Type.TABLOCK)
                .build();

        assertEquals(queryHint.getType(),TableHint.Type.TABLOCK);
    }


    // @formatter:off
    /**
     * FORCESEEK
     */
    public TableHint exampleB1 = $Forceseek();

    /**
     * FORCESEEK (PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID (SalesOrderID))
     */
    public TableHint exampleB2 = TableHintBuilder.$Forceseek("PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID","SalesOrderID");
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
    public TableHint exampleC = $Forcescan();
    // @formatter:on

    @Test
    public void testExampleC() {
        assertEquals(exampleC.getType(),TableHint.Type.FORCESCAN);
    }

}
