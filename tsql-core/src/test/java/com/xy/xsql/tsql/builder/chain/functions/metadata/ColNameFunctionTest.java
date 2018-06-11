package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.Col_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_col_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ColNameFunctionTest {


    /**
     * COL_NAME(OBJECT_ID('dbo.FactResellerSales'), 1)
     */
    public Col_Name example1 = f_col_name(
            c_string("dbo.FactResellerSales"),
            c_number(1)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getTableId().getClass(), StringConstant.class);
        assertEquals(example1.getColumnId().getClass(), NumberConstant.class);
    }

}