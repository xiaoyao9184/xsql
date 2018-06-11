package com.xy.xsql.tsql.builder.chain.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.datatype.DataLength;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_datalength;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DataLengthFunctionTest {


    /**
     * DATALENGTH(EnglishProductName)
     */
    public DataLength example1 = f_datalength(c("EnglishProductName"));

    @Test
    public void testExample1(){
        assertEquals(example1.getExpression().getClass(), ColumnName.class);
    }
}