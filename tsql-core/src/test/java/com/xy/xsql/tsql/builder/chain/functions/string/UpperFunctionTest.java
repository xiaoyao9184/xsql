package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.functions.string.RTrim;
import com.xy.xsql.tsql.model.functions.string.Upper;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_rtrim;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_upper;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class UpperFunctionTest {


    /**
     * UPPER(RTRIM(LastName))
     */
    public Upper example1 = f_upper(
            f_rtrim(c("LastName"))
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCharacterExpression().getClass(), RTrim.class);
    }

}