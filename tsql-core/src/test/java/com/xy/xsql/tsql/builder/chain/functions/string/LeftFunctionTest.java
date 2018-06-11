package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.string.Left;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_left;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LeftFunctionTest {


    /**
     * LEFT(Name, 5)
     */
    public Left exampleA = f_left(
            c("Name"),
            c_number(5)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCharacterExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getIntegerExpression().getClass(), NumberConstant.class);
    }

    /**
     * LEFT('abcdefg',2)
     */
    public Left exampleB = f_left(
            c_string("abcdefg"),
            c_number(5)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCharacterExpression().getClass(), StringConstant.class);
        assertEquals(exampleB.getIntegerExpression().getClass(), NumberConstant.class);
    }

    /**
     * LEFT(EnglishProductName, 5)
     */
    public Left exampleC = f_left(
            c("EnglishProductName"),
            c_number(5)
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getCharacterExpression().getClass(), ColumnName.class);
        assertEquals(exampleC.getIntegerExpression().getClass(), NumberConstant.class);
    }

    /**
     * LEFT('abcdefg',2)
     */
    public Left exampleD = f_left(
            c_string("abcdefg"),
            c_number(2)
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getCharacterExpression().getClass(), StringConstant.class);
        assertEquals(exampleD.getIntegerExpression().getClass(), NumberConstant.class);
    }

}