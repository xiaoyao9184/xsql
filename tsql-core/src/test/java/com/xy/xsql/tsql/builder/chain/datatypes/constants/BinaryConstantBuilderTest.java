package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_bin;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BinaryConstantBuilderTest {


    public BinaryConstant example1 = c_bin(
            javax.xml.bind.DatatypeConverter.parseHexBinary("AE")
    );
    public BinaryConstant example2 = c_bin(
            javax.xml.bind.DatatypeConverter.parseHexBinary("12Ef")
    );
    public BinaryConstant example3 = c_bin(
            javax.xml.bind.DatatypeConverter.parseHexBinary("69048AEFDD010E")
    );
    public BinaryConstant example4 = c_bin(new byte[0]);

    /**
     * 0xAE
     0x12Ef
     0x69048AEFDD010E
     0x  (empty binary string)
     */
    @Test
    public void testExample(){

        assertEquals(
                javax.xml.bind.DatatypeConverter.printHexBinary(example1.getData()),
                "AE");
        assertEquals(
                javax.xml.bind.DatatypeConverter.printHexBinary(example2.getData()),
                "12EF");
        assertEquals(
                javax.xml.bind.DatatypeConverter.printHexBinary(example3.getData()),
                "69048AEFDD010E");
        assertEquals(example4.getData().length,0);
    }
}