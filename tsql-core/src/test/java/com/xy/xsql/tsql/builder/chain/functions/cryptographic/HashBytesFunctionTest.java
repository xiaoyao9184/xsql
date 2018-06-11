package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.cryptographic.HashBytes;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_hashbytes;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class HashBytesFunctionTest {


    /**
     * HASHBYTES('SHA1', @HashThis)
     */
    public HashBytes exampleA = f_hashbytes(
            HashBytes.Algorithm.SHA1,
            e_variable("HashThis")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getAlgorithm(),HashBytes.Algorithm.SHA1);
        assertEquals(exampleA.getInputVariable().getName(), "HashThis");
    }

    /**
     * HASHBYTES('SHA1', c1)
     */
    public HashBytes exampleB = f_hashbytes(
            HashBytes.Algorithm.SHA1,
            c("c1")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getAlgorithm(),HashBytes.Algorithm.SHA1);
        assertEquals(exampleB.getInput().getClass(), ColumnName.class);
    }

}