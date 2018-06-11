package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.string.Len;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_len;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LenFunctionTest {


    /**
     * LEN(FirstName)
     */
    public Len example1 = f_len(
            c("FirstName")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getStringExpression().getClass(), ColumnName.class);
    }

}