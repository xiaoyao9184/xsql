package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.Grouping;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_grouping;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class GroupingFunctionTest {


    /**
     * GROUPING(SalesQuota)
     */
    public Grouping example1 = f_grouping(c("SalesQuota"));

    @Test
    public void testExample1(){
        assertEquals(example1.getColumnExpression().getClass(), ColumnName.class);
    }

}