package com.xy.xsql.tsql.builder.chain.functions.conversion;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.datetime.DateTime2;
import com.xy.xsql.tsql.model.datatypes.numeric.Money;
import com.xy.xsql.tsql.model.functions.conversion.Parse;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._datetime2;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._money;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_parse;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ParseFunctionTest {


    /**
     * PARSE('Monday, 13 December 2010' AS datetime2 USING 'en-US')
     */
    public Parse exampleA = f_parse(
            c_string("Monday, 13 December 2010"),
            _datetime2(),
            c_string("en-US"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getStringValue().getClass(), StringConstant.class);
        assertEquals(exampleA.getDataType().getClass(), DateTime2.class);
        assertEquals(exampleA.getCulture().getClass(), StringConstant.class);
    }

    /**
     * PARSE('€345,98' AS money USING 'de-DE')
     */
    public Parse exampleB = f_parse(
            c_string("€345,98"),
            _money(),
            c_string("de-DE"));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getStringValue().getClass(), StringConstant.class);
        assertEquals(exampleB.getDataType().getClass(), Money.class);
        assertEquals(exampleB.getCulture().getClass(), StringConstant.class);
    }

    /**
     * PARSE('12/16/2010' AS datetime2)
     */
    public Parse exampleC = f_parse(
            c_string("12/16/2010"),
            _datetime2());

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getStringValue().getClass(), StringConstant.class);
        assertEquals(exampleC.getDataType().getClass(), DateTime2.class);
    }

}