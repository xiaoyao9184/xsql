package com.xy.xsql.tsql.builder.chain.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.SmallInt;
import com.xy.xsql.tsql.model.functions.datatype.Identity;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._smallint;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_identity;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IdentityFunctionTest {


    /**
     * IDENTITY(smallint, 100, 1)
     */
    public Identity example1 = f_identity(
            _smallint(),
            e_number(100),
            e_number(1)
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getDataType().getClass(), SmallInt.class);
        assertEquals(example1.getSeed().getClass(), NumberConstant.class);
        assertEquals(example1.getIncrement().getClass(), NumberConstant.class);
    }

}