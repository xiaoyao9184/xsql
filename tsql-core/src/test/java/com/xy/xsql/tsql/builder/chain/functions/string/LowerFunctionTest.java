package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.functions.string.Lower;
import com.xy.xsql.tsql.model.functions.string.SubString;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_lower;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_substring;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LowerFunctionTest {


    /**
     * LOWER(SUBSTRING(EnglishProductName, 1, 20))
     */
    public Lower example1 = f_lower(
            f_substring(c("EnglishProductName"),c_number(1),c_number(20))
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCharacterExpression().getClass(), SubString.class);
    }

}