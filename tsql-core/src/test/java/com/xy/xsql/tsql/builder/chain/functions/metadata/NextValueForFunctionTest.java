package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.functions.metadata.NextValueFor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_nextvaluefor;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class NextValueForFunctionTest {


    /**
     * NEXT VALUE FOR Test.CountBy1
     */
    public NextValueFor exampleA = f_nextvaluefor(
            "Test","CountBy1"
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getSequenceName().size(), 2);
    }

    /**
     * NEXT VALUE FOR Test.CountBy1 OVER (ORDER BY LastName)
     */
    public NextValueFor exampleC = f_nextvaluefor(
            "Test","CountBy1",
            $Over()
                .$OrderBy(c("LastName"))
                .build()
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getSequenceName().size(), 2);
        assertNotNull(exampleC.getOver());
    }

}