package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.functions.system.Binary_Checksum;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_binary_checksum;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class BinaryChecksumFunctionTest {


    /**
     * BINARY_CHECKSUM(*)
     */
    public Binary_Checksum example1 = f_binary_checksum();

    @Test
    public void testExample(){
        assertEquals(example1.getExpressionList().size(), 0);
    }

}