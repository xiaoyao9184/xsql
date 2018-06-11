package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.functions.system.Checksum;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_checksum;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ChecksumFunctionTest {


    /**
     * BINARY_CHECKSUM(*)
     */
    public Checksum example1 = f_checksum(
            c("Name")
    );

    /**
     * CHECKSUM(N'Bearing Ball')
     */
    public Checksum example2 = f_checksum(
            c_n_string("Bearing Ball")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getExpressionList().size(), 1);
        assertEquals(example2.getExpressionList().size(), 1);
    }

}