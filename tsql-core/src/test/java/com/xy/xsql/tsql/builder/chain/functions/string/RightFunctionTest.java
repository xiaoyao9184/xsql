package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.string.Right;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_right;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class RightFunctionTest {


    /**
     * RIGHT(FirstName, 5)
     */
    public Right exampleA = f_right(
            c("FirstName"),
            c_number(5)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCharacterExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getIntegerExpression().getClass(), NumberConstant.class);
    }

    /**
     * RIGHT(LastName, 5)
     */
    public Right exampleB = f_right(
            c_string("LastName"),
            c_number(5)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCharacterExpression().getClass(), StringConstant.class);
        assertEquals(exampleB.getIntegerExpression().getClass(), NumberConstant.class);
    }

    /**
     * RIGHT('abcdefg',2)
     */
    public Right exampleC = f_right(
            c_string("abcdefg"),
            c_number(2)
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getCharacterExpression().getClass(), StringConstant.class);
        assertEquals(exampleC.getIntegerExpression().getClass(), NumberConstant.class);
    }

}