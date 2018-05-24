package com.xy.xsql.tsql.builder.chain.datatypes.table;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class ColumnNameFactoryTest {

    @Test
    public void testQuick(){
        // @formatter:off
        ColumnName quick = c("column");
        ColumnName quick2 = c("s","table","column");
        ColumnName quick3 = c(t("s","table"),"column");
        // @formatter:on

        assertEquals(quick.toString(), "column");
        assertEquals(quick2.toString(), "s.table.column");
        assertEquals(quick3.toString(), "s.table.column");
    }

}
