package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.string.String_Split;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_string_split;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StringSplitFunctionTest {


    /**
     * STRING_SPLIT(@tags, ',')
     */
    public String_Split exampleA = f_string_split(
            c("tags"),
            c_string(",")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getString().getClass(), ColumnName.class);
        assertEquals(exampleA.getSeparator().getClass(), StringConstant.class);
    }

    /**
     *  STRING_SPLIT(Tags, ',')
     */
    public String_Split exampleB = f_string_split(
            c("Tags"),
            c_string(",")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getString().getClass(), ColumnName.class);
        assertEquals(exampleB.getSeparator().getClass(), StringConstant.class);
    }

    /**
     * STRING_SPLIT('1,2,3',',')
     */
    public String_Split exampleE = f_string_split(
            c_string("1,2,3"),
            c_string(",")
    );

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getString().getClass(), StringConstant.class);
        assertEquals(exampleE.getSeparator().getClass(), StringConstant.class);
    }

}