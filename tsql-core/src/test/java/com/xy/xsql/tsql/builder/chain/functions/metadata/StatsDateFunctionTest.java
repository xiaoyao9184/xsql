package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.metadata.Stats_Date;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_stats_date;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StatsDateFunctionTest {


    /**
     * STATS_DATE(object_id, stats_id)
     */
    public Stats_Date exampleA = f_stats_date(
            c("object_id"),
            c("stats_id")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getObjectId().getClass(), ColumnName.class);
        assertEquals(exampleA.getStatsId().getClass(), ColumnName.class);
    }

}