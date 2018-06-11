package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.string.PatIndex;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_addition;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_patindex;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class PatIndexFunctionTest {


    /**
     * PATINDEX('%ter%', 'interesting data')
     */
    public PatIndex exampleA = f_patindex(
            c_string("%ter%"),
            c_string("interesting data")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getPattern().getClass(), StringConstant.class);
        assertEquals(exampleA.getExpression().getClass(), StringConstant.class);
    }

    /**
     * PATINDEX('%ensure%',DocumentSummary)
     */
    public PatIndex exampleB = f_patindex(
            c_string("%ensure%"),
            c("DocumentSummary")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getPattern().getClass(), StringConstant.class);
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
    }

    /**
     * PATINDEX('%en_ure%', 'please ensure the door is locked')
     */
    public PatIndex exampleC = f_patindex(
            c_string("%en_ure%"),
            c_string("please ensure the door is locked")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getPattern().getClass(), StringConstant.class);
        assertEquals(exampleC.getExpression().getClass(), StringConstant.class);
    }

    /**
     * PATINDEX ( '%ein%', 'Das ist ein Test'  COLLATE Latin1_General_BIN)
     */
    public PatIndex exampleD = f_patindex(
            c_string("%ein%"),
            //TODO String with COLLATE Expression
            c_string("Das ist ein Test")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getPattern().getClass(), StringConstant.class);
        assertEquals(exampleD.getExpression().getClass(), StringConstant.class);
    }

    /**
     * PATINDEX('%' + @MyValue + '%', DocumentSummary)
     */
    public PatIndex exampleE = f_patindex(
            e_addition(
                e_addition(
                    c_string("%"),
                    e_variable("MyValue")
                ),
                c_string("%")
            ),
            c("DocumentSummary")
    );

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getPattern().getClass(), BinaryExpression.class);
        assertEquals(exampleE.getExpression().getClass(), ColumnName.class);
    }

}