package com.xy.xsql.tsql.builder.chain.functions.text;

import com.xy.xsql.tsql.model.functions.text.TextPtr;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.TextFunctions.f_textptr;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TextPtrFunctionTest {


    /**
     * TEXTPTR(logo)
     */
    public TextPtr exampleA = f_textptr(
            c("logo")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getColumn().getFullName(), "logo");
    }

    /**
     * TEXTPTR(c2)
     */
    public TextPtr exampleB = f_textptr(
            c("c2")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getColumn().getFullName(), "c2");
    }


    /**
     * TEXTPTR(pr_info)
     */
    public TextPtr exampleC = f_textptr(
            c("pr_info")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getColumn().getFullName(), "pr_info");
    }

    /**
     * TEXTPTR(pr_info)
     */
    public TextPtr exampleD = f_textptr(
            c("pr_info")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getColumn().getFullName(), "pr_info");
    }

}