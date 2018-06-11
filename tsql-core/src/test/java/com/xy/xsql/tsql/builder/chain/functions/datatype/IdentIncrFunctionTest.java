package com.xy.xsql.tsql.builder.chain.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Incr;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_addition;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_ident_incr;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IdentIncrFunctionTest {


    /**
     * IDENT_INCR('Person.Address')
     */
    public Ident_Incr exampleA = f_ident_incr(c_string("Person.Address"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTableOrView().getClass(), StringConstant.class);
    }

    /**
     * IDENT_INCR(TABLE_SCHEMA + '.' + TABLE_NAME)
     */
    public Ident_Incr exampleB = f_ident_incr(
            e_addition(
                    e_addition(
                            c("TABLE_SCHEMA"),
                            c_string(".")
                    ),
                    c("TABLE_NAME")
            ));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTableOrView().getClass(), BinaryExpression.class);
    }
}