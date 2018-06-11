package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.functions.aggregate.CheckSum_Agg;
import com.xy.xsql.tsql.model.functions.conversion.Cast;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._int;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_checksum_agg;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_cast;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CheckSumAggFunctionTest {


    /**
     * CHECKSUM_AGG(CAST(Quantity AS int))
     */
    public CheckSum_Agg example1 = f_checksum_agg(
            f_cast(c("Quantity"),_int()));

    @Test
    public void testExample1(){
        assertEquals(example1.getExpression().getClass(), Cast.class);
    }


    /**
     * CHECKSUM_AGG(CAST(Quantity AS int))
     */
    public CheckSum_Agg example2 = f_checksum_agg(
            f_cast(c("Quantity"),_int()));

    @Test
    public void testExample2(){
        assertEquals(example2.getExpression().getClass(), Cast.class);
    }


}