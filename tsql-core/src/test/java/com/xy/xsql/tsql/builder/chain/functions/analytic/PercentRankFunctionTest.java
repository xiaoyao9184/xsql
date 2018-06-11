package com.xy.xsql.tsql.builder.chain.functions.analytic;

import com.xy.xsql.tsql.model.functions.analytic.Percent_Rank;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AnalyticFunctions.f_percent_rank;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class PercentRankFunctionTest {


    /**
     * PERCENT_RANK() OVER (PARTITION BY Department ORDER BY Rate )
     */
    public Percent_Rank example1 = f_percent_rank(
            $Over().$PartitionBy(c("Department")).$OrderBy(c("Rate")).build());

    @Test
    public void testExample1(){
        assertNotNull(example1.getOver());
    }
}