package com.xy.xsql.tsql.builder.chain.functions.logical;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.logical.IIf;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.functions.LogicalFunctions.f_iif;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IIfFunctionTest {


    /**
     * IIF ( @a > @b, 'TRUE', 'FALSE' )
     */
    public IIf exampleA = f_iif(
            e_grerater(
                    e_variable("a"),
                    e_variable("b")
            ),
            c_string("TRUE"),
            c_string("FALSE")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getBooleanExpression().getClass(), BinaryExpression.class);
        assertEquals(exampleA.getTrueValue().getClass(), StringConstant.class);
        assertEquals(exampleA.getFalseValue().getClass(), StringConstant.class);
    }

    /**
     * IIF ( 45 > 30, NULL, NULL )
     */
    public IIf exampleB = f_iif(
            e_grerater(
                    c_number(45),
                    c_number(30)
            ),
            e_null(),
            e_null()
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getBooleanExpression().getClass(), BinaryExpression.class);
        assertEquals(exampleB.getTrueValue().getClass(), Null.class);
        assertEquals(exampleB.getFalseValue().getClass(), Null.class);
    }

    /**
     * IIF ( 45 > 30, @p, @s )
     */
    public IIf exampleC = f_iif(
            e_grerater(
                    c_number(45),
                    c_number(30)
            ),
            e_variable("p"),
            e_variable("s")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getBooleanExpression().getClass(), BinaryExpression.class);
        assertEquals(exampleC.getTrueValue().getClass(), LocalVariable.class);
        assertEquals(exampleC.getFalseValue().getClass(), LocalVariable.class);
    }

}