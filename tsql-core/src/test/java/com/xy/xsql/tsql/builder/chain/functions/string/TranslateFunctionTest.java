package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Translate;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_translate;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TranslateFunctionTest {


    /**
     * TRANSLATE('2*[3+4]/{7-2}', '[]{}', '()()');
     */
    public Translate exampleA = f_translate(
            c_string("2*[3+4]/{7-2}"),
            c_string("[]{}"),
            c_string("()()")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getInputString().getClass(), StringConstant.class);
        assertEquals(exampleA.getCharacters().getClass(), StringConstant.class);
        assertEquals(exampleA.getTranslations().getClass(), StringConstant.class);
    }

    /**
     *  TRANSLATE('[137.4, 72.3]' , '[,]', '( )')
     */
    public Translate exampleB1 = f_translate(
            c_string("[137.4, 72.3]"),
            c_string("[,]"),
            c_string("( )")
    );

    /**
     * TRANSLATE('(137.4 72.3)' , '( )', '[,]')
     */
    public Translate exampleB2 = f_translate(
            c_string("(137.4 72.3)"),
            c_string("( )"),
            c_string("[,]")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB1.getInputString().getClass(), StringConstant.class);
        assertEquals(exampleB1.getCharacters().getClass(), StringConstant.class);
        assertEquals(exampleB1.getTranslations().getClass(), StringConstant.class);
        assertEquals(exampleB2.getInputString().getClass(), StringConstant.class);
        assertEquals(exampleB2.getCharacters().getClass(), StringConstant.class);
        assertEquals(exampleB2.getTranslations().getClass(), StringConstant.class);
    }

}