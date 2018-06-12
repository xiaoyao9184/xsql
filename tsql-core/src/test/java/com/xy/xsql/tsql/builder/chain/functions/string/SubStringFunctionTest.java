package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.string.SubString;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_substring;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SubStringFunctionTest {


    /**
     * SUBSTRING(name, 1, 1)
     */
    public SubString exampleA = f_substring(
            c("name"),
            c_number(1),
            c_number(1)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getStart().getClass(), NumberConstant.class);
        assertEquals(exampleA.getLength().getClass(), NumberConstant.class);
    }

    /**
     * SUBSTRING(logo, 1, 10)
     */
    public SubString exampleB1 = f_substring(
            c("logo"),
            c_number(1),
            c_number(10)
    );

    /**
     * SUBSTRING(pr.pr_info, 1, 35)
     */
    public SubString exampleB2 = f_substring(
            c("pr","pr_info"),
            c_number(1),
            c_number(35)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB1.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleB1.getStart().getClass(), NumberConstant.class);
        assertEquals(exampleB1.getLength().getClass(), NumberConstant.class);
        assertEquals(exampleB2.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleB2.getStart().getClass(), NumberConstant.class);
        assertEquals(exampleB2.getLength().getClass(), NumberConstant.class);
    }

}