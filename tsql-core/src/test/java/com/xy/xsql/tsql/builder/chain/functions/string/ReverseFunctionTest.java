package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.string.Reverse;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_reverse;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ReverseFunctionTest {


    /**
     * REVERSE(FirstName)
     */
    public Reverse example1 = f_reverse(
            c("FirstName")
    );

    /**
     * REVERSE(@myvar)
     */
    public Reverse example2 = f_reverse(
            e_variable("myvar")
    );

    /**
     * REVERSE(1234)
     */
    public Reverse example3 = f_reverse(
            e_number(1234)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getStringExpression().getClass(), ColumnName.class);
        assertEquals(example2.getStringExpression().getClass(), LocalVariable.class);
        assertEquals(example3.getStringExpression().getClass(), NumberConstant.class);
    }

}