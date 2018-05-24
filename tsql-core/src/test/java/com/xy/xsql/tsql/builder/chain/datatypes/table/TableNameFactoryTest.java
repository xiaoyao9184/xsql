package com.xy.xsql.tsql.builder.chain.datatypes.table;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class TableNameFactoryTest {

    @Test
    public void testQuick(){
        // @formatter:off
        TableName quick = t("table");
        TableName quick2 = t("s","table");
        // @formatter:on

        assertEquals(quick.toString(), "table");
        assertEquals(quick2.toString(), "s.table");
    }

}
