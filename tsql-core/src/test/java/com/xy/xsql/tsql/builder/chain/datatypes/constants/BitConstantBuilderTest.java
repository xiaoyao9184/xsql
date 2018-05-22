package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.BitConstant;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_bit;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BitConstantBuilderTest {


    public BitConstant example1 = c_bit(true);
    public BitConstant example2 = c_bit(false);

    /**
     *
     */
    @Test
    public void testExample(){
        assertEquals(
                example1.getaByte().intValue(),
                Integer.valueOf(1).intValue());
        assertEquals(
                example2.getaByte().intValue(),
                Integer.valueOf(0).intValue());
    }
}