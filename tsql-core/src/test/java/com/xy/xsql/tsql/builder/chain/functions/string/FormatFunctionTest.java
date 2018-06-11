package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.conversion.Cast;
import com.xy.xsql.tsql.model.functions.string.Format;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.*;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._time;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_cast;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_format;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FormatFunctionTest {


    /**
     * FORMAT ( @d, 'd', 'en-US' )
     */
    public Format exampleA = f_format(
            e_variable("d"),
            c_string("d"),
            c_string("en-US")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getValue().getClass(), LocalVariable.class);
        assertEquals(exampleA.getFormat().getClass(), StringConstant.class);
        assertEquals(exampleA.getCulture().getClass(), StringConstant.class);
    }

    /**
     * FORMAT(123456789,'###-##-####')
     */
    public Format exampleB = f_format(
            c_number(123456789),
            c_string("###-##-####")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getValue().getClass(), NumberConstant.class);
        assertEquals(exampleB.getFormat().getClass(), StringConstant.class);
    }

    /**
     * FORMAT(cast('07:35' as time), N'hh.mm')
     */
    public Format exampleD = f_format(
            f_cast(c_string("07:35"),_time()),
            c_n_string("hh.mm")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getValue().getClass(), Cast.class);
        assertEquals(exampleD.getFormat().getClass(), StringConstant.class);
    }

}