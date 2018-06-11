package com.xy.xsql.tsql.builder.chain.functions.analytic;

import com.xy.xsql.tsql.model.functions.analytic.Cume_Dist;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AnalyticFunctions.f_cume_dist;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CumeDistFunctionTest {


    /**
     * CUME_DIST () OVER (PARTITION BY Department ORDER BY Rate)
     */
    public Cume_Dist example1 = f_cume_dist($Over().$PartitionBy(c("Department")).$OrderBy(c("Rate")).build());

    @Test
    public void testExample1(){
        assertNotNull(example1.getOrderBy());
    }

}