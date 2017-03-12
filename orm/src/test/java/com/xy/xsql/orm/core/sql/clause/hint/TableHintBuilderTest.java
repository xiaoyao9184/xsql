package com.xy.xsql.orm.core.sql.clause.hint;

import com.xy.xsql.orm.core.sql.clause.hints.TableHintBuilder;
import com.xy.xsql.orm.data.sql.clause.hints.TableHint;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.clause.hints.TableHintBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableHintBuilderTest {

    /**
     * WITH (TABLOCK)
     */
    @Test
    public void testExampleA() {
        new TableHintBuilder()
                .withType(TableHint.Type.TABLOCK)
                .build();

        TableHint queryHint = TABLOCK();

        Assert.assertEquals(queryHint.getType(),TableHint.Type.TABLOCK);
    }


    /**
     * WITH (FORCESEEK)
     * WITH (FORCESEEK (PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID (SalesOrderID)))
     */
    @Test
    public void testExampleB() {
        TableHint queryHint = FORCESEEK();
        TableHint queryHint2 = FORCESEEK("PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID","SalesOrderID");

        Assert.assertEquals(queryHint.getType(),TableHint.Type.FORCESEEK);
        Assert.assertEquals(queryHint2.getType(),TableHint.Type.FORCESEEK);
        Assert.assertEquals(queryHint2.getIndex_value().get(0).toString(),"PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID");
        Assert.assertEquals(queryHint2.getIndex_column_name().get(0).toString(),"SalesOrderID");
    }

    /**
     * WITH (FORCESCAN)
     */
    @Test
    public void testExampleC() {
        TableHint queryHint = FORCESCAN();

        Assert.assertEquals(queryHint.getType(),TableHint.Type.FORCESCAN);
    }

}
