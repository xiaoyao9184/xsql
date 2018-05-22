package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_negative_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_positive_number;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class NumberConstantBuilderTest {

    public NumberConstant example1 = c_number(1894);
    public NumberConstant example2 = c_number(1);
    public NumberConstant example3 = c_number(1894.1204);
    public NumberConstant example4 = c_number(2.0);
    public NumberConstant example5 = c_number(101.5E5);
    public NumberConstant example6 = c_number(0.5E-2);
    public NumberConstant example7 = c_positive_number(145345234);
    public NumberConstant example8 = c_negative_number(-2147483648);
    public NumberConstant example9 = c_positive_number(145345234.2234);
    public NumberConstant example10 = c_negative_number(2147483648.10);
    public NumberConstant example11 = c_positive_number(123E-3);
    public NumberConstant example12 = c_negative_number(12E5);


    /**
     *
     */
    @Test
    public void testExample(){
        assertEquals(example1.getNumber().toString(),"1894");
        assertEquals(example2.getNumber().toString(),"1");
        assertEquals(example3.getNumber().toString(), "1894.1204");
        assertEquals(example4.getNumber().toString(), "2.0");
        assertEquals(example5.getNumber().toString(), "1.015E7");
        assertEquals(example6.getNumber().toString(), "0.005");
        assertEquals(example7.getNumber().toString(), "145345234");
        assertTrue(example7.isUsePositive());
        assertEquals(example8.getNumber().toString(), "-2147483648");
        assertTrue(example8.isUseNegative());
        assertEquals(example9.getNumber().toString(), "1.453452342234E8");
        assertTrue(example9.isUsePositive());
        assertEquals(example10.getNumber().toString(), "2.1474836481E9");
        assertTrue(example10.isUseNegative());
        assertEquals(example11.getNumber().toString(), "0.123");
        assertTrue(example11.isUsePositive());
        assertEquals(example12.getNumber().toString(), "1200000.0");
        assertTrue(example12.isUseNegative());
    }

}