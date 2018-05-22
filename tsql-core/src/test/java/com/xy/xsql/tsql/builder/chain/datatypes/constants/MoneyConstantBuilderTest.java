package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.MoneyConstant;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_money;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_positive_money;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class MoneyConstantBuilderTest {

    public MoneyConstant example1 = c_money("$",12);
    public MoneyConstant example2 = c_money("$",542023.14);
    public MoneyConstant example3 = c_money("$",-45.56);
    public MoneyConstant example4 = c_positive_money("$",423456.99);

    /**
     *
     */
    @Test
    public void testExample(){
        assertEquals(example1.getSymbol(),"$");
        assertEquals(example1.getNumber().toString(), "12");
        assertEquals(example2.getSymbol(),"$");
        assertEquals(example2.getNumber().toString(), "542023.14");
        assertEquals(example3.getNumber().toString(), "-45.56");
        assertEquals(example3.isUseNegative(),true);
        assertEquals(example4.getNumber().toString(), "423456.99");
        assertEquals(example4.isUsePositive(),true);
        assertEquals(example4.isUseNegative(),false);
    }
}