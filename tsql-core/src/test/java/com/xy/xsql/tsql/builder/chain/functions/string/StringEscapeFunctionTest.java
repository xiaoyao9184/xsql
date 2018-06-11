package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.string.String_Escape;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_string_escape;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StringEscapeFunctionTest {


    /**
     * STRING_ESCAPE('\   /
     \\    "     ', 'json')
     */
    public String_Escape exampleA = f_string_escape(
            c_string("\\   /\n" +
                    "     \\\\    \"     "),
            String_Escape.Type.json
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getText().getClass(), StringConstant.class);
    }

    /**
     * STRING_ESCAPE(@name,'json')
     */
    public String_Escape exampleB = f_string_escape(
            e_variable("name"),
            String_Escape.Type.json
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getText().getClass(), LocalVariable.class);
    }

}