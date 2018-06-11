package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.system.FormatMessage;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_formatmessage;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FormatMessageFunctionTest {


    /**
     * FORMATMESSAGE(20009, 'First Variable', 'Second Variable')
     */
    public FormatMessage exampleA = f_formatmessage(
            c_number(20009),
            c_string("First Variable"),
            c_string("Second Variable")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getMsgMumber().getClass(), NumberConstant.class);
        assertEquals(exampleA.getParamValueList().size(), 2);
    }

    /**
     * FORMATMESSAGE('This is the %s and this is the %s.', 'first variable', 'second variable')
     */
    public FormatMessage exampleB = f_formatmessage(
            c_string("This is the %s and this is the %s."),
            c_string("first variable"),
            c_string("second variable")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getMsgString().getString(), "This is the %s and this is the %s.");
        assertEquals(exampleB.getParamValueList().size(), 2);
    }


    //ExampleC ignore
}