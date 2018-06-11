package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Square;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_square;
import static org.junit.Assert.assertEquals;

/*reated by xiaoyao9184 on 2018/6/5.
 */
public class SquareFunctionTest {

    /**
     * SQUARE(@r)
     */
    public Square example1 = f_square(
            e_variable("r")
    );

    /**
     * SQUARE(Volume)
     */
    public Square example2 = f_square(
            c("Volume")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(example2.getFloatExpression().getClass(), ColumnName.class);
    }

}