package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.system.IsNumeric;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_isnumeric;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsNumericFunctionTest {


    /**
     * ISNUMERIC(PostalCode)
     */
    public IsNumeric example1 = f_isnumeric(
            c("PostalCode")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getExpression().getClass(), ColumnName.class);
    }

}